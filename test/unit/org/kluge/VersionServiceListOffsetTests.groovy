package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(VersionService)
@Mock(Version)
class VersionServiceListOffsetTests {

    @Before
    void setUp() {

        20.times {
            VersionMock.mock(it).save(failOnError: true)
        }

    }

    void testOk() {

        def params = [offset: '15']
        def result = service.list(params)
        assertNotNull "'result' should not be null", result
        def items = result.items
        assertNotNull "'items' should not be null", items
        assertEquals "'size' should be 5", 5, items.size()

    }

    void testNull() {

        def params = [offset: null]
        def result = service.list(params)
        assertNotNull "'result' should not be null", result
        def items = result.items
        assertNotNull "'items' should not be null", items
        assertEquals "'size' should be 10", 10, items.size()

    }

    void testBlank() {

        def params = [offset: '']
        def result = service.list(params)
        assertNotNull "'result' should not be null", result
        def items = result.items
        assertNotNull "'items' should not be null", items
        assertEquals "'size' should be 10", 10, items.size()

    }

    void testInvalid() {

        def params = [offset: 'A']
        def result = service.list(params)
        assertNotNull "'result' should not be null", result
        def items = result.items
        assertNotNull "'items' should not be null", items
        assertEquals "'size' should be 10", 10, items.size()

    }

}