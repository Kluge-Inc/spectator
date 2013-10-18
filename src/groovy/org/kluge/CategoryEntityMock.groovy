package org.kluge

class CategoryEntityMock {

    static CategoryEntity mock(id) {

        def instance = new CategoryEntity(
                name: 'A',
                documents: null,
        )
        instance

    }

}