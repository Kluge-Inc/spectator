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
class VersionServiceCreate {

    @Pointcut(
        value='execution(void org.kluge.VersionService.create(..)) && bean(versionService) && args(version)',
        argNames='version')
    public void create( Version version ) {}

    @Before('create(version)')
    void before( Version version ) {
        log.info( "Begins request: ${version}" )
    }

    @AfterReturning(
        pointcut='create(org.kluge.Version)')
    void afterReturning() {
        log.info( "End of request" )
    }

    @AfterThrowing(
        pointcut='create(org.kluge.Version)',
        throwing='e' )
    void afterThrowing( Exception e ) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info( message.toString() )

    }

}