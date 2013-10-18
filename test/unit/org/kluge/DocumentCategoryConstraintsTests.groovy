package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(Document)
class DocumentCategoryConstraintsTests {

    @Before
    void setUp() {
        mockForConstraintsTests(Document, [new Document()])
    }

    void testNullable() {

        def instance = new Document()
        instance.category = null
        assertFalse "'validate' should be false", instance.validate()
        assertNotNull "'errors[ 'category' ]' should not be null",
                instance.errors['category']
        assertEquals "'errors[ 'category' ]' should be 'nullable'",
                'nullable', instance.errors['category']

    }

}