package com.epam.xstack.aspects.trainee_aspects.end_points_aspects;

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
public class SaveTraineeEndPointAspect {
    @Pointcut("@annotation(com.epam.xstack.aspects.trainee_aspects.end_points_aspects.annotations.SaveTraineeEndPointAspectAnnotation)")
    public void saveTraineePointCut() {
    }

    @Before("saveTraineePointCut()")
    public void beforeAdvice() {
        log.info("Save Trainee end point called");
    }

    @After("saveTraineePointCut()")
    public void afterAdvice(JoinPoint joinPoint) {
        log.info("Save Trainee end point ended");
    }
}
