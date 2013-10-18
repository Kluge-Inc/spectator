package org.kluge

class Document {
    String name

    static belongsTo = [category:CategoryEntity]
    static hasOne = [activeVersion:Version]
    static hasMany = [versions:Version]

    static constraints = {
        activeVersion nullable: true
        name blank: false
    }
}