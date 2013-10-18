package org.kluge.aop

import org.kluge.CategoryEntity

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

import org.springframework.stereotype.Component

@Component
@Aspect
class CategoryEntityServiceGet {

    @Pointcut(
            value = 'execution(org.kluge.CategoryEntity org.kluge.CategoryEntityService.get(..)) && bean(categoryEntityService) && args(id)',
            argNames = 'id')
    public void getMethod(Long id) {}

    @Before('getMethod(id)')
    void before(Long id) {
        log.info("Begins request:${id}")
    }

    @AfterReturning(
            pointcut = 'getMethod(Long)',
            returning = 'categoryEntity')
    void afterReturning(CategoryEntity categoryEntity) {
        log.info("End of request: ${categoryEntity}")
    }

    @AfterThrowing(
            pointcut = 'getMethod(Long)',
            throwing = 'e')
    void afterThrowing(Exception e) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info(message.toString())

    }

}