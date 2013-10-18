package org.kluge

class CategoryEntityController {

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

    def categoryEntityService
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

        def model = [categoryEntityInstance: new CategoryEntity(params)]
        render(template: 'form', model: model)

    }

    def save() {

        def categoryEntity = new CategoryEntity(params)
        saveOnDb(categoryEntity, 'create',
                'categoryEntity.created.message')

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
        map.categoryEntityInstance.properties = params
        map.edit = true
        saveOnDb(map.categoryEntityInstance, 'update',
                'categoryEntity.updated.message', true)

    }

    def delete(Long id) {

        def map = get(id)
        if (!map) return
        categoryEntityService.delete(map.categoryEntityInstance)
        flash.listMessage = message(code: 'default.deleted.message',
                args: [message(code: 'categoryEntity.label',
                        default: 'CategoryEntity'), id])
        redirect(action: 'content')

    }

    private void renderList(template) {

        def model = [:]
        def result = categoryEntityService.list(params)
        model.items = result.items
        model.total = result.total
        render(template: template, model: model)

    }

    private Map get(Long id) {

        if (id == null) {
            notifyCrack()
            return null
        }
        def categoryEntity = categoryEntityService.get(id)
        if (!categoryEntity) {
            notifyCrack()
            return null
        }
        [categoryEntityInstance: categoryEntity]

    }

    private void saveOnDb(categoryEntity, method, msg, edit = false) {

        try {
            categoryEntityService."${method}"(categoryEntity)
        } catch (IllegalArgumentException e) {
            response.status = 400
            render(template: 'form', model: [categoryEntityInstance: categoryEntity,
                    edit: edit])
            return
        }
        flash.formMessage = message(
                code: "default.${edit ? 'updated' : 'created'}.message",
                args: [message(code: 'categoryEntity.label',
                        default: 'CategoryEntity'), categoryEntity.id])
        redirect(action: 'edit', id: categoryEntity.id)

    }

    private void notifyCrack() {

        crackingService.notify(request, params)
        redirect(controller: 'logout')

    }

}