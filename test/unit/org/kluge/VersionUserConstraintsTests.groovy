package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(Version)
class VersionUserConstraintsTests {

    @Before
    void setUp() {
        mockForConstraintsTests(Version, [new Version()])
    }

    void testNullable() {

        def instance = new Version()
        instance.user = null
        assertFalse "'validate' should be false", instance.validate()
        assertNotNull "'errors[ 'user' ]' should not be null",
                instance.errors['user']
        assertEquals "'errors[ 'user' ]' should be 'nullable'",
                'nullable', instance.errors['user']

    }

}