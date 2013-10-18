package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(Version)
class VersionFileConstraintsTests {

    @Before
    void setUp() {
        mockForConstraintsTests(Version, [new Version()])
    }

    void testMaxSize() {

        def instance = new Version()
        instance.file = new byte[52428801]
        assertFalse "'validate' should be false", instance.validate()
        assertNotNull "'errors[ 'file' ]' should not be null",
                instance.errors['file']
        assertEquals "'errors[ 'file' ]' should be 'maxSize'",
                'maxSize', instance.errors['file']

    }

    void testNullable() {

        def instance = new Version()
        instance.file = null
        assertFalse "'validate' should be false", instance.validate()
        assertNotNull "'errors[ 'file' ]' should not be null",
                instance.errors['file']
        assertEquals "'errors[ 'file' ]' should be 'nullable'",
                'nullable', instance.errors['file']

    }

}