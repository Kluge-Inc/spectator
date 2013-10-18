package org.kluge

import grails.test.mixin.*
import org.junit.*
import org.junit.rules.*

@TestFor(CategoryEntityService)
@Mock(CategoryEntity)
class CategoryEntityServiceDeleteTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none()

    @Before
    void setUp() {

        CategoryEntityMock.mock(0).save(failOnError: true)

    }

    void testOk() {

        assertEquals "'count' should be 1", 1, CategoryEntity.count()
        def instance = service.get(1)
        service.delete(instance)
        assertEquals "'count' should be 0", 0, CategoryEntity.count()

    }

    void testNull() {

        thrown.expect(IllegalArgumentException)
        thrown.expectMessage("Parameter 'categoryEntity' is null")
        service.delete(null)

    }

    void testInvalid() {

        def instance = new CategoryEntity()
        assertFalse "'exists' should be false",
                CategoryEntity.exists(instance.id)
        service.delete(instance)

    }

}