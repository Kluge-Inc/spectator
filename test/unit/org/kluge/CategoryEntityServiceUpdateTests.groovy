package org.kluge

import grails.test.mixin.*
import org.junit.*
import org.junit.rules.*

@TestFor(CategoryEntityService)
@Mock(CategoryEntity)
class CategoryEntityServiceUpdateTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none()

    void testOk() {

        def instance = CategoryEntityMock.mock(0)
        assertEquals "'count' should be 0", 0, CategoryEntity.count()
        service.update(instance)
        assertEquals "'count' should be 1", 1, CategoryEntity.count()

    }

    void testCategoryEntityNull() {

        def instance = null
        thrown.expect(IllegalArgumentException)
        thrown.expectMessage("Parameter 'categoryEntity' is null")
        service.update(instance)

    }

    void testCategoryEntityInvalid() {

        def instance = CategoryEntityMock.mock(0)
        instance.name = null
        thrown.expect(IllegalArgumentException)
        thrown.expectMessage("Parameter 'categoryEntity' is invalid")
        service.update(instance)

    }

}