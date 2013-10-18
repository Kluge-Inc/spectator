package org.kluge

import javax.servlet.http.HttpServletRequest
import grails.test.GrailsMock
import grails.test.mixin.*
import org.junit.*

@TestFor(CategoryEntityController)
@Mock(CategoryEntity)
class CategoryEntityControllerEditTests {

    @Before
    void setUp() {

        CategoryEntityMock.mock(0).save(failOnError: true)
        views['/categoryEntity/_form.gsp'] = getTemplate()

    }

    void testOk() {

        def control = mockCategoryEntityService()
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

        def control = mockCategoryEntityService()
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
        '<g:if test="${categoryEntityInstance}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockCategoryEntityService() {

        def control = mockFor(CategoryEntityService)
        control.demand.get(1) { Long id ->
            CategoryEntity.findById(id)
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

}