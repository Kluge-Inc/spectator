package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(Document)
class DocumentVersionsConstraintsTests {

    @Before
    void setUp() {
        mockForConstraintsTests(Document, [new Document()])
    }

    void testNullable() {

        def instance = new Document()
        instance.versions = null
        assertFalse "'validate' should be false", instance.validate()
        assertNull "'errors[ 'versions' ]' should be null",
                instance.errors['versions']

    }

}