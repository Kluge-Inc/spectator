package org.kluge

class DocumentMock {

    static Document mock(id) {

        def instance = new Document(
                activeVersion: null,
                name: 'A',
                category: new CategoryEntity(),
                versions: null,
        )
        instance

    }

}