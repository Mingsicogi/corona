package com.mins.corona.common.aspect;

import com.mins.corona.common.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RedisCacheAspect {

    private final RedisService redisService;

    @Around(value = "@annotation(com.mins.corona.common.annotation.RedisCache)")
    public Object get(ProceedingJoinPoint joinPoint) {
        log.info(">>>>>> start redis get");
        try {
            // @RedisCache 가 사용된 메소드 정보를 알기 위해 해당 메소드를 객체로 가져옴.
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            // Redis에 사용될 key를 만듬. 메소드 명 + 해당 메소드에서 사용된 모든 파라미터 (구분자 ':')
            StringBuilder key = new StringBuilder(method.getName());
            key.append(":");
            for (int i = 0; i < method.getParameters().length; i++) {
                key.append(method.getParameters()[i].getName());
                key.append(":");
            }

            // Redis에 먼저 데이터가 있는지 확인
            Object redisInfo = redisService.get(key.toString(), method.getReturnType());
            if(redisInfo == null) { // Redis에 데이터가 없으면 데이터를 redis에 올리고,
                log.info(">>>>> data load on redis");
                redisService.put(key.toString(), joinPoint.proceed());
                return joinPoint.proceed();
            } else { // 있으면 죄회한 값을 리턴함.
                log.info(">>>>>> get data from redis");
                return redisInfo;
            }
        } catch (Throwable throwable) {
            throw new RuntimeException();
        }
    }
}
