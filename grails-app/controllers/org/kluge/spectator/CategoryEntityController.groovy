package org.kluge.spectator



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CategoryEntityController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CategoryEntity.list(params), model:[categoryEntityInstanceCount: CategoryEntity.count()]
    }

    def show(CategoryEntity categoryEntityInstance) {
        respond categoryEntityInstance
    }

    def create() {
        respond new CategoryEntity(params)
    }

    @Transactional
    def save(CategoryEntity categoryEntityInstance) {
        if (categoryEntityInstance == null) {
            notFound()
            return
        }

        if (categoryEntityInstance.hasErrors()) {
            respond categoryEntityInstance.errors, view:'create'
            return
        }

        categoryEntityInstance.save flush:true
        rabbitSend 'notifier', null, 'category created'


        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'categoryEntityInstance.label', default: 'CategoryEntity'), categoryEntityInstance.id])
                redirect categoryEntityInstance
            }
            '*' { respond categoryEntityInstance, [status: CREATED] }
        }
    }

    def edit(CategoryEntity categoryEntityInstance) {
        respond categoryEntityInstance
    }

    @Transactional
    def update(CategoryEntity categoryEntityInstance) {
        if (categoryEntityInstance == null) {
            notFound()
            return
        }

        if (categoryEntityInstance.hasErrors()) {
            respond categoryEntityInstance.errors, view:'edit'
            return
        }

        categoryEntityInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'CategoryEntity.label', default: 'CategoryEntity'), categoryEntityInstance.id])
                redirect categoryEntityInstance
            }
            '*'{ respond categoryEntityInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(CategoryEntity categoryEntityInstance) {

        if (categoryEntityInstance == null) {
            notFound()
            return
        }

        categoryEntityInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'CategoryEntity.label', default: 'CategoryEntity'), categoryEntityInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'categoryEntityInstance.label', default: 'CategoryEntity'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
