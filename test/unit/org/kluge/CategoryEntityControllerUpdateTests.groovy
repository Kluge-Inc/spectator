package org.kluge

import javax.servlet.http.HttpServletRequest
import grails.test.GrailsMock
import grails.test.mixin.*
import org.junit.*

@TestFor(CategoryEntityController)
@Mock(CategoryEntity)
class CategoryEntityControllerUpdateTests {

    @Before
    void setUp() {

        CategoryEntityMock.mock(0).save(failOnError: true)
        views['/categoryEntity/_form.gsp'] = getTemplate()

    }

    void testOk() {

        def control = mockCategoryEntityService()
        request.method = 'POST'
        setUpParams()
        controller.update(1)
        def expected = 'default.updated.message'
        assertEquals "'message' should be '${expected}'",
                expected, flash.formMessage
        expected = "/categoryEntity/edit/${1}"
        assertEquals "'redirectedUrl' should be '${expected}'",
                expected, response.redirectedUrl
        assertEquals "'status' should be 302", 302, response.status
        control.verify()

    }

    void testIdNull() {

        def control = mockCrackingService()
        request.method = 'GET'
        controller.update(null)
        def expected = '/logout'
        assertEquals "'redirectedUrl' should be '${expected}'",
                expected, response.redirectedUrl
        assertEquals "'status' should be 302", 302, response.status
        control.verify()
    }

    void testNotFound() {

        def control = mockCategoryEntityService(true, 0)
        def control2 = mockCrackingService()
        request.method = 'GET'
        controller.update(2)
        def expected = '/logout'
        assertEquals "'redirectedUrl' should be '${expected}'",
                expected, response.redirectedUrl
        assertEquals "'status' should be 302", 302, response.status
        control.verify()
        control2.verify()

    }

    void testParamsInvalid() {

        def control = mockCategoryEntityService(false)
        request.method = 'POST'
        setUpParams()
        params.name = null
        controller.update(1)
        def expected = 'OK'
        assertEquals "'text' should be '${expected}'",
                expected, response.text
        assertEquals "'status' should be 400", 400, response.status
        control.verify()

    }

    @Ignore('See http://jira.grails.org/browse/GRAILS-8426')
    void testRequestMethodInvalid() {

        request.method = 'GET'
        controller.update(1)
        assertEquals "'status' should be 405", 405, response.status

    }

    private String getTemplate() {
        '<g:if test="${categoryEntityInstance && edit}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockCategoryEntityService(update = true, updateTimes = 1) {

        def control = mockFor(CategoryEntityService)
        control.demand.get(1) { Long id ->
            CategoryEntity.findById(id)
        }
        control.demand.update(updateTimes) { CategoryEntity instance ->
            if (update) {
                instance.save(failOnError: true)
            } else throw new IllegalArgumentException('error')
        }
        controller.categoryEntityService = control.createMock()
        control

    }

    private GrailsMock mockCrackingService() {

        def control = mockFor(CrackingService)
        control.demand.notify(1) { HttpServletRequest request, Map params -> }
        controller.crackingService = control.createMock()
        control

    }

    private void setUpParams() {

        def mock = CategoryEntityMock.mock(0)
        mock.properties.each { params."${it.key}" = it.value }

    }

}