package com.Ryan.Blog.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class logAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.Ryan.Blog.controller.*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(JoinPoint jp){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String method = jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName();
        Object[] args = jp.getArgs();
        RequestLog requestLog = new RequestLog(url,ip,method,args);
        logger.info("RequestLog : {}",requestLog);
    }

    @AfterReturning(pointcut = "pointCut()",returning = "result")
    /*返回控制器方法执行完后的返回的结果*/
    public void afterReturning(Object result){
        logger.info("result : {}",result);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class RequestLog{
        private String url;
        private String ip;
        private String method;
        private Object[] args;
    }
}
