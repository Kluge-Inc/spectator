package org.kluge

import grails.test.GrailsMock
import grails.test.mixin.*
import org.junit.*

@TestFor(DocumentController)
@Mock(Document)
class DocumentControllerSaveTests {

    @Before
    void setUp() {
        views['/document/_form.gsp'] = getTemplate()
    }

    void testOk() {

        def control = mockDocumentService()
        request.method = 'POST'
        setUpParams()
        controller.save()
        def expected = 'default.created.message'
        assertEquals "'message' should be '${expected}'",
                expected, flash.formMessage
        expected = "/document/edit/1"
        assertEquals "'redirectedUrl' should be '${expected}'",
                expected, response.redirectedUrl
        assertEquals "'status' should be 302", 302, response.status
        control.verify()

    }

    void testParamsInvalid() {

        def control = mockDocumentService(false)
        request.method = 'POST'
        setUpParams()
        params.name = null
        controller.save()
        def expected = 'OK'
        assertEquals "'text' should be '${expected}'",
                expected, response.text
        assertEquals "'status' should be 400", 400, response.status
        control.verify()

    }

    @Ignore('See http://jira.grails.org/browse/GRAILS-8426')
    void testRequestMethodInvalid() {

        request.method = 'GET'
        controller.save()
        assertEquals "'status' should be 405", 405, response.status

    }

    private String getTemplate() {
        '<g:if test="${documentInstance}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockDocumentService(save = true) {

        def control = mockFor(DocumentService)
        control.demand.create(1) { Document instance ->
            if (save) {
                instance.id = 1
                instance.save(failOnError: true)
            } else throw new IllegalArgumentException('error')
        }
        controller.documentService = control.createMock()
        control

    }

    private void setUpParams() {

        def mock = DocumentMock.mock(0)
        mock.properties.each { params."${it.key}" = it.value }

    }

}