package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(CategoryEntityService)
@Mock(CategoryEntity)
class CategoryEntityServiceListSortOrderTests {

    @Before
    void setUp() {

        20.times {
            CategoryEntityMock.mock(it + 1).save(failOnError: true)
        }

    }

    void testOk() {

        def params = [sort: 'id', order: 'desc']
        def result = service.list(params)
        def items = result.items
        def item = items[0]
        def expected = 20
        assertEquals "'id' should be '${expected}'",
                expected, item.id

    }

    void testNull() {

        def params = [sort: null, order: null]
        def result = service.list(params)
        assertNotNull "'result' should not be null", result
        def items = result.items
        assertNotNull "'items' should not be null", items
        assertEquals "'size' should be 10", 10, items.size()

    }

    void testBlank() {

        def params = [sort: '', order: '']
        def result = service.list(params)
        assertNotNull "'result' should not be null", result
        def items = result.items
        assertNotNull "'items' should not be null", items
        assertEquals "'size' should be 10", 10, items.size()

    }

    void testInvalid() {

        def params = [sort: 'A', order: 'A']
        def result = service.list(params)
        assertNotNull "'result' should not be null", result
        def items = result.items
        assertNotNull "'items' should not be null", items
        assertEquals "'size' should be 10", 10, items.size()

    }

}