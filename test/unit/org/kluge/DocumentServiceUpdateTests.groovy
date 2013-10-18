package org.kluge

import grails.test.mixin.*
import org.junit.*
import org.junit.rules.*

@TestFor(DocumentService)
@Mock(Document)
class DocumentServiceUpdateTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none()

    void testOk() {

        def instance = DocumentMock.mock(0)
        assertEquals "'count' should be 0", 0, Document.count()
        service.update(instance)
        assertEquals "'count' should be 1", 1, Document.count()

    }

    void testDocumentNull() {

        def instance = null
        thrown.expect(IllegalArgumentException)
        thrown.expectMessage("Parameter 'document' is null")
        service.update(instance)

    }

    void testDocumentInvalid() {

        def instance = DocumentMock.mock(0)
        instance.name = null
        thrown.expect(IllegalArgumentException)
        thrown.expectMessage("Parameter 'document' is invalid")
        service.update(instance)

    }

}