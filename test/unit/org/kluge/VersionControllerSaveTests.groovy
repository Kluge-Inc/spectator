package org.kluge

import grails.test.GrailsMock
import grails.test.mixin.*
import org.junit.*

@TestFor(VersionController)
@Mock(Version)
class VersionControllerSaveTests {

    @Before
    void setUp() {
        views['/version/_form.gsp'] = getTemplate()
    }

    void testOk() {

        def control = mockVersionService()
        request.method = 'POST'
        setUpParams()
        controller.save()
        def expected = 'default.created.message'
        assertEquals "'message' should be '${expected}'",
                expected, flash.formMessage
        expected = "/version/edit/1"
        assertEquals "'redirectedUrl' should be '${expected}'",
                expected, response.redirectedUrl
        assertEquals "'status' should be 302", 302, response.status
        control.verify()

    }

    void testParamsInvalid() {

        def control = mockVersionService(false)
        request.method = 'POST'
        setUpParams()
        params.document = null
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
        '<g:if test="${versionInstance}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockVersionService(save = true) {

        def control = mockFor(VersionService)
        control.demand.create(1) { Version instance ->
            if (save) {
                instance.id = 1
                instance.save(failOnError: true)
            } else throw new IllegalArgumentException('error')
        }
        controller.versionService = control.createMock()
        control

    }

    private void setUpParams() {

        def mock = VersionMock.mock(0)
        mock.properties.each { params."${it.key}" = it.value }

    }

}