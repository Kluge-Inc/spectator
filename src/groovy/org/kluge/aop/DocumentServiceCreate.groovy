package org.kluge.aop

import org.kluge.Document

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

import org.springframework.stereotype.Component

@Component
@Aspect
class DocumentServiceCreate {

    @Pointcut(
            value = 'execution(void org.kluge.DocumentService.create(..)) && bean(documentService) && args(document)',
            argNames = 'document')
    public void create(Document document) {}

    @Before('create(document)')
    void before(Document document) {
        log.info("Begins request: ${document}")
    }

    @AfterReturning(
            pointcut = 'create(org.kluge.Document)')
    void afterReturning() {
        log.info("End of request")
    }

    @AfterThrowing(
            pointcut = 'create(org.kluge.Document)',
            throwing = 'e')
    void afterThrowing(Exception e) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info(message.toString())

    }

}