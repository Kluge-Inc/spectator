package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(VersionService)
@Mock(Version)
class VersionServiceListMaxTests {

    @Before
    void setUp() {

        20.times {
            VersionMock.mock(it).save(failOnError: true)
        }

    }

    void testLowValue() {

        def params = [max: '9']
        def result = service.list(params)
        assertNotNull "'result' should not be null", result
        def items = result.items
        assertNotNull "'items' should not be null", items
        assertEquals "'size' should be 9", 9, items.size()

    }

    void testHighValue() {

        def params = [max: '11']
        def result = service.list(params)
        assertNotNull "'result' should not be null", result
        def items = result.items
        assertNotNull "'items' should not be null", items
        assertEquals "'size' should be 10", 10, items.size()

    }

    void testNull() {

        def params = [max: null]
        def result = service.list(params)
        assertNotNull "'result' should not be null", result
        def items = result.items
        assertNotNull "'items' should not be null", items
        assertEquals "'size' should be 10", 10, items.size()

    }

    void testBlank() {

        def params = [max: '']
        def result = service.list(params)
        assertNotNull "'result' should not be null", result
        def items = result.items
        assertNotNull "'items' should not be null", items
        assertEquals "'size' should be 10", 10, items.size()

    }

    void testInvalid() {

        def params = [max: 'A']
        def result = service.list(params)
        assertNotNull "'result' should not be null", result
        def items = result.items
        assertNotNull "'items' should not be null", items
        assertEquals "'size' should be 10", 10, items.size()

    }

}