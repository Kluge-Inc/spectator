package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(Document)
class DocumentActiveVersionConstraintsTests {

    @Before
    void setUp() {
        mockForConstraintsTests(Document, [new Document()])
    }

    void testNullable() {

        def instance = new Document()
        instance.activeVersion = null
        assertFalse "'validate' should be false", instance.validate()
        assertNull "'errors[ 'activeVersion' ]' should be null",
                instance.errors['activeVersion']

    }

}