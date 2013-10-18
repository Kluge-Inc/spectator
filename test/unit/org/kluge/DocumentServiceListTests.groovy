package org.kluge

import grails.test.mixin.*
import org.junit.*

@TestFor(DocumentService)
@Mock(Document)
class DocumentServiceListTests {

    void testOk() {

        def params = [:]
        def result = service.list(params)
        assertNotNull "'result' should not be null", result
        def items = result.items
        assertNotNull "'items' should not be null", items
        def total = result.total
        assertNotNull "'total' should not be null", total

    }

}