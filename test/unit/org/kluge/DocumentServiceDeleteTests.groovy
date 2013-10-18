package org.kluge

import grails.test.mixin.*
import org.junit.*
import org.junit.rules.*

@TestFor(DocumentService)
@Mock(Document)
class DocumentServiceDeleteTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none()

    @Before
    void setUp() {

        DocumentMock.mock(0).save(failOnError: true)

    }

    void testOk() {

        assertEquals "'count' should be 1", 1, Document.count()
        def instance = service.get(1)
        service.delete(instance)
        assertEquals "'count' should be 0", 0, Document.count()

    }

    void testNull() {

        thrown.expect(IllegalArgumentException)
        thrown.expectMessage("Parameter 'document' is null")
        service.delete(null)

    }

    void testInvalid() {

        def instance = new Document()
        assertFalse "'exists' should be false",
                Document.exists(instance.id)
        service.delete(instance)

    }

}