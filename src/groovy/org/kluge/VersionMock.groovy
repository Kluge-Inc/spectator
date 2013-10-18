package org.kluge

class VersionMock {

    static Version mock( id ) {

        def instance = new Version(
            document:new Document(),
            name:'A',
            date:new Date(),
            file:new byte[ 1 ],
            user:'A',
        )
        instance

    }

}