package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(CategoryEntityController)
class CategoryEntityControllerIndexTests {

    void testOk() {

        request.method = 'GET'
        controller.index()
        def expected = '/categoryEntity/content'
        assertEquals "'redirectedUrl' should be '${expected}'",
                expected, response.redirectedUrl
        assertEquals "'status' should be 302", 302, response.status

    }

    void testOkWithParams() {

        request.method = 'GET'
        params.name = 'value'
        controller.index()
        def expected = '/categoryEntity/content?name=value'
        assertEquals "'redirectedUrl' should be '${expected}'",
                expected, response.redirectedUrl
        assertEquals "'status' should be 302", 302, response.status

    }

    @Ignore('See http://jira.grails.org/browse/GRAILS-8426')
    void testRequestMethodInvalid() {

        request.method = 'POST'
        controller.index()
        assertEquals "'status' should be 405", 405, response.status

    }

}