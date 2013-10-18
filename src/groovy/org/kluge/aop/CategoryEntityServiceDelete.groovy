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
class CategoryEntityServiceDelete {

    @Pointcut(
            value = 'execution(void org.kluge.CategoryEntityService.delete(..)) && bean(categoryEntityService) && args(categoryEntity)',
            argNames = 'categoryEntity')
    public void delete(CategoryEntity categoryEntity) {}

    @Before('delete(categoryEntity)')
    void before(CategoryEntity categoryEntity) {
        log.info("Begins request:${categoryEntity}")
    }

    @AfterReturning(
            pointcut = 'delete(org.kluge.CategoryEntity)')
    void afterReturning() {
        log.info("End of request")
    }

    @AfterThrowing(
            pointcut = 'delete(org.kluge.CategoryEntity)',
            throwing = 'e')
    void afterThrowing(Exception e) {

        def message = '' << ''
        message << "Error in request"
        message << ": ${e.class.simpleName} - ${e.message}"
        log.info(message.toString())

    }

}