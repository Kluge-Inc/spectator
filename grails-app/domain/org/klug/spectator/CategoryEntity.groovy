package org.klug.spectator

class CategoryEntity {
    String name

    static hasMany = [documents: Document]

    static constraints = {
        name blank: false
    }
}
