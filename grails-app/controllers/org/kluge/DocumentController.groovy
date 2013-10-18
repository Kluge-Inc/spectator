package org.kluge

class DocumentController {

    static allowedMethods = [
            index: 'GET',
            content: 'GET',
            list: 'GET',
            create: 'GET',
            save: 'POST',
            edit: 'GET',
            update: 'POST',
            delete: 'POST'
    ]

    def documentService
    def crackingService

    def index() {
        redirect(action: 'content', params: params)
    }

    def content() {
        renderList('content')
    }

    def list() {
        renderList('list')
    }

    def create() {

        def model = [documentInstance: new Document(params)]
        render(template: 'form', model: model)

    }

    def save() {

        def document = new Document(params)
        saveOnDb(document, 'create',
                'document.created.message')

    }

    def edit(Long id) {

        def map = get(id)
        if (!map) return
        map.edit = true
        render(template: 'form', model: map)

    }

    def update(Long id) {

        def map = get(id)
        if (!map) return
        map.documentInstance.properties = params
        map.edit = true
        saveOnDb(map.documentInstance, 'update',
                'document.updated.message', true)

    }

    def delete(Long id) {

        def map = get(id)
        if (!map) return
        documentService.delete(map.documentInstance)
        flash.listMessage = message(code: 'default.deleted.message',
                args: [message(code: 'document.label',
                        default: 'Document'), id])
        redirect(action: 'content')

    }

    private void renderList(template) {

        def model = [:]
        def result = documentService.list(params)
        model.items = result.items
        model.total = result.total
        render(template: template, model: model)

    }

    private Map get(Long id) {

        if (id == null) {
            notifyCrack()
            return null
        }
        def document = documentService.get(id)
        if (!document) {
            notifyCrack()
            return null
        }
        [documentInstance: document]

    }

    private void saveOnDb(document, method, msg, edit = false) {

        try {
            documentService."${method}"(document)
        } catch (IllegalArgumentException e) {
            response.status = 400
            render(template: 'form', model: [documentInstance: document,
                    edit: edit])
            return
        }
        flash.formMessage = message(
                code: "default.${edit ? 'updated' : 'created'}.message",
                args: [message(code: 'document.label',
                        default: 'Document'), document.id])
        redirect(action: 'edit', id: document.id)

    }

    private void notifyCrack() {

        crackingService.notify(request, params)
        redirect(controller: 'logout')

    }

}