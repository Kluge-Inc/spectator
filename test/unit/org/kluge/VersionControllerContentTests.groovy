package org.kluge

import grails.test.GrailsMock
import grails.test.mixin.*
import org.junit.*

@TestFor(VersionController)
class VersionControllerContentTests {

    @Before
    void setUp() {
        views['/version/_content.gsp'] = getTemplate()
    }

    void testOk() {

        def control = mockVersionService()
        request.method = 'GET'
        def model = controller.content()
        def expected = 'OK'
        assertEquals "'text' should be '${expected}'",
                expected, response.text
        assertEquals "'status' should be 200", 200, response.status
        control.verify()

    }

    @Ignore('See http://jira.grails.org/browse/GRAILS-8426')
    void testRequestMethodInvalid() {

        request.method = 'POST'
        controller.content()
        assertEquals "'status' should be 405", 405, response.status

    }

    private String getTemplate() {
        '<g:if test="${items && total}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockVersionService() {

        def control = mockFor(VersionService)
        control.demand.list(1) { Map params ->
            [items: [new Version()], total: 1] }
        controller.versionService = control.createMock()
        control

    }

}