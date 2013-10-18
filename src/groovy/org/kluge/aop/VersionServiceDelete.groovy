package org.kluge.aop

import org.kluge.Version

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

import org.springframework.stereotype.Component

@Component
@Aspect
class VersionServiceDelete {

    @Pointcut(
        value='execution(void org.kluge.VersionService.delete(..)) && bean(versionService) && args(version)',
        argNames='version')
    public void delete( Version version ) {}

    @Before('delete(version)')
    void before( Version version ) {
        log.info( "Begins request:${version}" )
    }

    @AfterReturning(
        pointcut='delete(org.kluge.Version)')
    void afterReturning() {
        log.info( "End of request" )
    }

    @AfterThrowing(
        pointcut='delete(org.kluge.Version)',
        throwing='e' )
    void afterThrowing( Exception e ) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info( message.toString() )

    }

}