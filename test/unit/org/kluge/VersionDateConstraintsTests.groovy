package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(Version)
class VersionDateConstraintsTests {

    @Before
    void setUp() {
        mockForConstraintsTests(Version, [new Version()])
    }

    void testNullable() {

        def instance = new Version()
        instance.date = null
        assertFalse "'validate' should be false", instance.validate()
        assertNotNull "'errors[ 'date' ]' should not be null",
                instance.errors['date']
        assertEquals "'errors[ 'date' ]' should be 'nullable'",
                'nullable', instance.errors['date']

    }

}