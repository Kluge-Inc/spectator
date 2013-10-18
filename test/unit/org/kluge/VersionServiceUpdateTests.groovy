package org.kluge

import grails.test.mixin.*
import org.junit.*
import org.junit.rules.*

@TestFor(VersionService)
@Mock(Version)
class VersionServiceUpdateTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none()

    void testOk() {

        def instance = VersionMock.mock(0)
        assertEquals "'count' should be 0", 0, Version.count()
        service.update(instance)
        assertEquals "'count' should be 1", 1, Version.count()

    }

    void testVersionNull() {

        def instance = null
        thrown.expect(IllegalArgumentException)
        thrown.expectMessage("Parameter 'version' is null")
        service.update(instance)

    }

    void testVersionInvalid() {

        def instance = VersionMock.mock(0)
        instance.document = null
        thrown.expect(IllegalArgumentException)
        thrown.expectMessage("Parameter 'version' is invalid")
        service.update(instance)

    }

}