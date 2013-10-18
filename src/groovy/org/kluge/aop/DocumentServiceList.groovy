package org.kluge.aop

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

import org.springframework.stereotype.Component

@Component
@Aspect
class DocumentServiceList {

    @Pointcut(
            value = 'execution(java.util.Map org.kluge.DocumentService.list(..)) && bean(documentService) && args(params)',
            argNames = 'params')
    public void list(Map params) {}

    @Before('list(params)')
    void before(Map params) {
        log.info("Begins request: ${params}")
    }

    @AfterReturning(
            pointcut = 'list(java.util.Map)',
            returning = 'map')
    void afterReturning(Map map) {
        log.info("End of request: ${map}")
    }

    @AfterThrowing(
            pointcut = 'list(java.util.Map)',
            throwing = 'e')
    void afterThrowing(Exception e) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info(message.toString())

    }

}