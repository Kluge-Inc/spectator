package org.kluge.spectator

class Version {
    String name
    String user
    Date date
    byte[] file

    static belongsTo = [document:Document]

    static constraints = {
        document nullable: false
        name blank: false
        date nullable: false
        file maxSize: 1024 * 1024 * 50
    }
}
