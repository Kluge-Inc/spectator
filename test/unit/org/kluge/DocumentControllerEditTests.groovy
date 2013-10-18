package org.kluge

import javax.servlet.http.HttpServletRequest
import grails.test.GrailsMock
import grails.test.mixin.*
import org.junit.*

@TestFor(DocumentController)
@Mock(Document)
class DocumentControllerEditTests {

    @Before
    void setUp() {

        DocumentMock.mock(0).save(failOnError: true)
        views['/document/_form.gsp'] = getTemplate()

    }

    void testOk() {

        def control = mockDocumentService()
        request.method = 'GET'
        def model = controller.edit(1)
        def expected = 'OK'
        assertEquals "'text' should be '${expected}'",
                expected, response.text
        assertEquals "'status' should be 200", 200, response.status
        control.verify()

    }

    void testIdNull() {

        def control = mockCrackingService()
        request.method = 'GET'
        controller.edit(null)
        def expected = '/logout'
        assertEquals "'redirectedUrl' should be '${expected}'",
                expected, response.redirectedUrl
        assertEquals "'status' should be 302", 302, response.status
        control.verify()
    }

    void testNotFound() {

        def control = mockDocumentService()
        def control2 = mockCrackingService()
        request.method = 'GET'
        controller.edit(2)
        def expected = '/logout'
        assertEquals "'redirectedUrl' should be '${expected}'",
                expected, response.redirectedUrl
        assertEquals "'status' should be 302", 302, response.status
        control.verify()
        control2.verify()

    }

    @Ignore('See http://jira.grails.org/browse/GRAILS-8426')
    void testRequestMethodInvalid() {

        request.method = 'POST'
        controller.edit(1)
        assertEquals "'status' should be 405", 405, response.status

    }

    private String getTemplate() {
        '<g:if test="${documentInstance}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockDocumentService() {

        def control = mockFor(DocumentService)
        control.demand.get(1) { Long id ->
            Document.findById(id)
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