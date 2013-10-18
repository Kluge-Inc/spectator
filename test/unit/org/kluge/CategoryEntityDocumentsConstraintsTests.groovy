package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(CategoryEntity)
class CategoryEntityDocumentsConstraintsTests {

    @Before
    void setUp() {
        mockForConstraintsTests(CategoryEntity, [new CategoryEntity()])
    }

    void testNullable() {

        def instance = new CategoryEntity()
        instance.documents = null
        assertFalse "'validate' should be false", instance.validate()
        assertNull "'errors[ 'documents' ]' should be null",
                instance.errors['documents']

    }

}