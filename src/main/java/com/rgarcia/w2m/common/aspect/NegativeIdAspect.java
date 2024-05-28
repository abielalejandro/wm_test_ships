package com.rgarcia.w2m.common.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Slf4j
@Component
@Aspect
public class NegativeIdAspect {
    @Pointcut("@annotation(com.rgarcia.w2m.common.NegativeIdParam)")
    public void negativeIdPointcut(){}

    @Before("negativeIdPointcut() && args(id,..)")
    public void negativeIdMethodCallsAdviceBefore(JoinPoint pjp, Long id) throws Throwable {

        if (Optional.ofNullable(id).isPresent() && Long.compare(id, 0)<0) {
            String line = String.format("Id param [%s] is negative",id) ;
            log.info(line);
        }


    }
}
