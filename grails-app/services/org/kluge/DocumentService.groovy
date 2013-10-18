package org.kluge

import grails.gorm.DetachedCriteria
import grails.validation.ValidationException

class DocumentService {

    Map list(Map params) {

        processParams(params)
        def criteria = new DetachedCriteria(Document).build {}
        [items: criteria.list(params), total: criteria.count()]

    }

    void create(Document document) {
        save(document)
    }

    void update(Document document) {
        save(document)
    }

    Document get(Long id) {

        if (id == null) throw new IllegalArgumentException(
                "Parameter 'id' is null")
        Document.findById(id)

    }

    void delete(Document document) {

        if (document == null) throw new IllegalArgumentException(
                "Parameter 'document' is null")
        document.delete()

    }

    private void processParams(params) {

        params.max = ListUtils.parseMax(params.max)
        params.offset = ListUtils.parseOffset(params.offset)
        params.order = ListUtils.parseOrder(params.order)
        def fields = ['name', 'id']
        params.sort = ListUtils.parseSort(params.sort, fields)

    }

    private void save(Document document) {

        if (!document) throw new IllegalArgumentException(
                "Parameter 'document' is null")
        try {
            document.save(failOnError: true)
        } catch (ValidationException) {
            throw new IllegalArgumentException(
                    "Parameter 'document' is invalid")
        }

    }

}