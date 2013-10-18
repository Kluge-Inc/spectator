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
class DocumentServiceDelete {

    @Pointcut(
            value = 'execution(void org.kluge.DocumentService.delete(..)) && bean(documentService) && args(document)',
            argNames = 'document')
    public void delete(Document document) {}

    @Before('delete(document)')
    void before(Document document) {
        log.info("Begins request:${document}")
    }

    @AfterReturning(
            pointcut = 'delete(org.kluge.Document)')
    void afterReturning() {
        log.info("End of request")
    }

    @AfterThrowing(
            pointcut = 'delete(org.kluge.Document)',
            throwing = 'e')
    void afterThrowing(Exception e) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info(message.toString())

    }

}