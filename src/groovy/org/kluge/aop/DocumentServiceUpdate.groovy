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
class DocumentServiceUpdate {

    @Pointcut(
            value = 'execution(void org.kluge.DocumentService.update(..)) && bean(documentService) && args(document)',
            argNames = 'document')
    public void update(Document document) {}

    @Before('update(document)')
    void before(Document document) {
        log.info("Begins request: ${document}")
    }

    @AfterReturning(
            pointcut = 'update(org.kluge.Document)')
    void afterReturning() {
        log.info("End of request")
    }

    @AfterThrowing(
            pointcut = 'update(org.kluge.Document)',
            throwing = 'e')
    void afterThrowing(Exception e) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info(message.toString())

    }

}