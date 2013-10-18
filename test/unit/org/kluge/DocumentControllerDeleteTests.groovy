package org.kluge

import javax.servlet.http.HttpServletRequest
import grails.test.GrailsMock
import grails.test.mixin.*
import org.junit.*

@TestFor(DocumentController)
@Mock(Document)
class DocumentControllerDeleteTests {

    @Before
    void setUp() {

        DocumentMock.mock(0).save(failOnError: true)

    }

    void testOk() {

        def control = mockDocumentService()
        request.method = 'POST'
        controller.delete(1)
        def expected = 'default.deleted.message'
        assertEquals "'message' should be '${expected}'",
                expected, flash.listMessage
        expected = '/document/content'
        assertEquals "'redirectedUrl' should be '${expected}'",
                expected, response.redirectedUrl
        assertEquals "'status' should be 302", 302, response.status
        control.verify()

    }

    void testIdNull() {

        def control = mockCrackingService()
        request.method = 'POST'
        controller.delete(null)
        def expected = '/logout'
        assertEquals "'redirectedUrl' should be '${expected}'",
                expected, response.redirectedUrl
        assertEquals "'status' should be 302", 302, response.status
        control.verify()
    }

    void testNotFound() {

        def control = mockDocumentService(false)
        def control2 = mockCrackingService()
        request.method = 'POST'
        controller.delete(2)
        def expected = '/logout'
        assertEquals "'redirectedUrl' should be '${expected}'",
                expected, response.redirectedUrl
        assertEquals "'status' should be 302", 302, response.status
        control.verify()
        control2.verify()

    }

    @Ignore('See http://jira.grails.org/browse/GRAILS-8426')
    void testRequestMethodInvalid() {

        request.method = 'GET'
        controller.delete(1)
        assertEquals "'status' should be 405", 405, response.status

    }

    private GrailsMock mockDocumentService(delete = true) {

        def control = mockFor(DocumentService)
        control.demand.get(1) { Long id ->
            Document.findById(id)
        }
        if (delete) {
            control.demand.delete(1) { Document instance ->
                instance.delete()
            }
        }
        controller.documentService = control.createMock()
        control

    }

    private GrailsMock mockCrackingService() {

        def control = mockFor(CrackingService)
        control.demand.notify(1) { HttpServletRequest request, Map params -> }
        controller.crackingService = control.createMock()
        control

    }

}