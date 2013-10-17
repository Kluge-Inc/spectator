package org.kluge.spectator



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VersionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Version.list(params), model: [versionInstanceCount: Version.count()]
    }

    def show(Version versionInstance) {
        respond versionInstance
    }

    def create() {
        respond new Version(params)
    }

    @Transactional
    def save(Version versionInstance) {
        if (versionInstance == null) {
            notFound()
            return
        }

        if (versionInstance.hasErrors()) {
            respond versionInstance.errors, view: 'create'
            return
        }

        versionInstance.document.activeVersion = versionInstance
        versionInstance.save flush: true

        rabbitSend 'notifier', null, 'version created'

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'versionInstance.label', default: 'Version'), versionInstance.id])
                redirect versionInstance
            }
            '*' { respond versionInstance, [status: CREATED] }
        }
    }

    def edit(Version versionInstance) {
        respond versionInstance
    }

    @Transactional
    def update(Version versionInstance) {
        if (versionInstance == null) {
            notFound()
            return
        }

        if (versionInstance.hasErrors()) {
            respond versionInstance.errors, view: 'edit'
            return
        }

        versionInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Version.label', default: 'Version'), versionInstance.id])
                redirect versionInstance
            }
            '*' { respond versionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Version versionInstance) {

        if (versionInstance == null) {
            notFound()
            return
        }

        versionInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Version.label', default: 'Version'), versionInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'versionInstance.label', default: 'Version'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
