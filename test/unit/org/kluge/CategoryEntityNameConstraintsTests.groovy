package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(CategoryEntity)
class CategoryEntityNameConstraintsTests {

    @Before
    void setUp() {
        mockForConstraintsTests(CategoryEntity, [new CategoryEntity()])
    }

    void testBlank() {

        def instance = new CategoryEntity()
        instance.name = ''
        assertFalse "'validate' should be false", instance.validate()
        assertNotNull "'errors[ 'name' ]' should not be null",
                instance.errors['name']
        assertEquals "'errors[ 'name' ]' should be 'blank'",
                'blank', instance.errors['name']

    }

    void testNullable() {

        def instance = new CategoryEntity()
        instance.name = null
        assertFalse "'validate' should be false", instance.validate()
        assertNotNull "'errors[ 'name' ]' should not be null",
                instance.errors['name']
        assertEquals "'errors[ 'name' ]' should be 'nullable'",
                'nullable', instance.errors['name']

    }

}