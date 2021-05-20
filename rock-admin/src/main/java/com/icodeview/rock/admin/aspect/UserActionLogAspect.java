package com.icodeview.rock.admin.aspect;

import com.icodeview.rock.admin.annotation.UserActionLog;
import com.icodeview.rock.admin.pojo.RbacUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class UserActionLogAspect {
    @Pointcut("@annotation(com.icodeview.rock.admin.annotation.UserActionLog)")
    public void logPointCut(){
    }
    @AfterReturning("logPointCut()")
    public void userActionLog(JoinPoint joinPoint){
        RbacUser rbacUser = (RbacUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        UserActionLog annotation = method.getAnnotation(UserActionLog.class);
        String operation = annotation.value();
        log.error(operation);
    }
}
