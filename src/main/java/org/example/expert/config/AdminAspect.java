package org.example.expert.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AdminAspect {

    private final HttpServletRequest httpServletRequest;
    private final ObjectMapper objectMapper;

    @Pointcut("execution(* org.example.expert.*.*.*.CommentAdminService.deleteComment(..)) || execution(* org.example.expert.*.*.*.UserAdminService.changeUserRole(..))")
    public void adminOperations() {}

    @Around("adminOperations()")
    public Object logAdminOperations(ProceedingJoinPoint joinPoint) throws Throwable {

        Object userId = httpServletRequest.getAttribute("userId");
        log.info("요청 id = {}", userId);

        Object[] args = joinPoint.getArgs();
        String requestBody = objectMapper.writeValueAsString(args[1]);
        log.info("요청 Body(JSON) = {}", requestBody);

        Object result = joinPoint.proceed();
        String responseBody = objectMapper.writeValueAsString(result);
        log.info("응답 Body(JSON) = {}", responseBody);

        return result;
    }

}
