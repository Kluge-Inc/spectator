package org.kluge

import grails.test.mixin.*
import org.junit.*
import org.junit.rules.*

@TestFor(VersionService)
@Mock(Version)
class VersionServiceGetTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none()

    @Before
    void setUp() {
        VersionMock.mock(0).save(failOnError: true)
    }

    void testOk() {

        def result = service.get(1)
        assertNotNull "'result' should not be null", result

    }

    void testIdNull() {

        thrown.expect(IllegalArgumentException)
        thrown.expectMessage("Parameter 'id' is null")
        service.get(null)

    }

    void testNotFound() {

        def result = service.get(2)
        assertNull "'result ' should be null", result

    }

}