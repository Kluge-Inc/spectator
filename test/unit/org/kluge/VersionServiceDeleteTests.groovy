package org.kluge

import grails.test.mixin.*
import org.junit.*
import org.junit.rules.*

@TestFor(VersionService)
@Mock(Version)
class VersionServiceDeleteTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none()

    @Before
    void setUp() {

        VersionMock.mock(0).save(failOnError: true)

    }

    void testOk() {

        assertEquals "'count' should be 1", 1, Version.count()
        def instance = service.get(1)
        service.delete(instance)
        assertEquals "'count' should be 0", 0, Version.count()

    }

    void testNull() {

        thrown.expect(IllegalArgumentException)
        thrown.expectMessage("Parameter 'version' is null")
        service.delete(null)

    }

    void testInvalid() {

        def instance = new Version()
        assertFalse "'exists' should be false",
                Version.exists(instance.id)
        service.delete(instance)

    }

}