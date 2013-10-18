package org.kluge
import grails.gorm.DetachedCriteria

class CategoryEntityService {

    Map list(Map params) {

        processParams(params)
        def criteria = new DetachedCriteria(CategoryEntity).build {}
        [items: criteria.list(params), total: criteria.count()]

    }

    void create(CategoryEntity categoryEntity) {
        save(categoryEntity)
    }

    void update(CategoryEntity categoryEntity) {
        save(categoryEntity)
    }

    CategoryEntity get(Long id) {

        if (id == null) throw new IllegalArgumentException(
                "Parameter 'id' is null")
        CategoryEntity.findById(id)

    }

    void delete(CategoryEntity categoryEntity) {

        if (categoryEntity == null) throw new IllegalArgumentException(
                "Parameter 'categoryEntity' is null")
        categoryEntity.delete()

    }

    private void processParams(params) {

        params.max = ListUtils.parseMax(params.max)
        params.offset = ListUtils.parseOffset(params.offset)
        params.order = ListUtils.parseOrder(params.order)
        def fields = ['name', 'id']
        params.sort = ListUtils.parseSort(params.sort, fields)

    }

    private void save(CategoryEntity categoryEntity) {

        if (!categoryEntity) throw new IllegalArgumentException(
                "Parameter 'categoryEntity' is null")
        try {
            categoryEntity.save(failOnError: true)
        } catch (ValidationException) {
            throw new IllegalArgumentException(
                    "Parameter 'categoryEntity' is invalid")
        }

    }

}