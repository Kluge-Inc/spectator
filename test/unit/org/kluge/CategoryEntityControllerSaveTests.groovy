package org.kluge

import grails.test.GrailsMock
import grails.test.mixin.*
import org.junit.*

@TestFor(CategoryEntityController)
@Mock(CategoryEntity)
class CategoryEntityControllerSaveTests {

    @Before
    void setUp() {
        views['/categoryEntity/_form.gsp'] = getTemplate()
    }

    void testOk() {

        def control = mockCategoryEntityService()
        request.method = 'POST'
        setUpParams()
        controller.save()
        def expected = 'default.created.message'
        assertEquals "'message' should be '${expected}'",
                expected, flash.formMessage
        expected = "/categoryEntity/edit/1"
        assertEquals "'redirectedUrl' should be '${expected}'",
                expected, response.redirectedUrl
        assertEquals "'status' should be 302", 302, response.status
        control.verify()

    }

    void testParamsInvalid() {

        def control = mockCategoryEntityService(false)
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
        '<g:if test="${categoryEntityInstance}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockCategoryEntityService(save = true) {

        def control = mockFor(CategoryEntityService)
        control.demand.create(1) { CategoryEntity instance ->
            if (save) {
                instance.id = 1
                instance.save(failOnError: true)
            } else throw new IllegalArgumentException('error')
        }
        controller.categoryEntityService = control.createMock()
        control

    }

    private void setUpParams() {

        def mock = CategoryEntityMock.mock(0)
        mock.properties.each { params."${it.key}" = it.value }

    }

}