package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(Version)
class VersionNameConstraintsTests {

    @Before
    void setUp() {
        mockForConstraintsTests(Version, [new Version()])
    }

    void testBlank() {

        def instance = new Version()
        instance.name = ''
        assertFalse "'validate' should be false", instance.validate()
        assertNotNull "'errors[ 'name' ]' should not be null",
                instance.errors['name']
        assertEquals "'errors[ 'name' ]' should be 'blank'",
                'blank', instance.errors['name']

    }

    void testNullable() {

        def instance = new Version()
        instance.name = null
        assertFalse "'validate' should be false", instance.validate()
        assertNotNull "'errors[ 'name' ]' should not be null",
                instance.errors['name']
        assertEquals "'errors[ 'name' ]' should be 'nullable'",
                'nullable', instance.errors['name']

    }

}