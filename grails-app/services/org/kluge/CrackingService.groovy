package org.kluge

import javax.servlet.http.HttpServletRequest

class CrackingService {

    void notify( HttpServletRequest request, Map params ) {

        def message = "Request ${request.requestURL}"
        message << " from ${request.remoteAddr}"
        message << " and params ${params}"
        message << " has been detected as unusual activity"
        println message.toString()

    }

}