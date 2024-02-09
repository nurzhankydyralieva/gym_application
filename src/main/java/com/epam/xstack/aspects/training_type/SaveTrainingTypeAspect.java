package com.epam.xstack.aspects.training_type;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class SaveTrainingTypeAspect {
    @Pointcut("@annotation(com.epam.xstack.aspects.training_type.annotations.SaveTrainingTypeAspectAnnotation)")
    public void saveTrainingTypePointCut() {
    }

    @Before("saveTrainingTypePointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("Before save Training type method invoked: " + joinPoint.getSignature());
    }

    @After("saveTrainingTypePointCut()")
    public void afterAdvice(JoinPoint joinPoint) {
        log.info("After save Training type method invoked: " + joinPoint.getSignature());
    }
}
