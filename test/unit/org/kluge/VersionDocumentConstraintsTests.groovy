package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(Version)
class VersionDocumentConstraintsTests {

    @Before
    void setUp() {
        mockForConstraintsTests(Version, [new Version()])
    }

    void testNullable() {

        def instance = new Version()
        instance.document = null
        assertFalse "'validate' should be false", instance.validate()
        assertNotNull "'errors[ 'document' ]' should not be null",
                instance.errors['document']
        assertEquals "'errors[ 'document' ]' should be 'nullable'",
                'nullable', instance.errors['document']

    }

}