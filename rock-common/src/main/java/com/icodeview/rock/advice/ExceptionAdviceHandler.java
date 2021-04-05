package com.icodeview.rock.advice;


import com.icodeview.rock.exception.BadHttpRequestException;
import com.icodeview.rock.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class ExceptionAdviceHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handlerException(MethodArgumentNotValidException exception){
        log.error("验证错误！");
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        return CommonResult.exception(0,message);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult handlerException(ConstraintViolationException exception){
        Set<ConstraintViolation<?>> set = exception.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = set.iterator();
        ConstraintViolation<?> next = iterator.next();
        String message = next.getMessage();
        return CommonResult.exception(0,message);
    }
    @ExceptionHandler(BadHttpRequestException.class)
    public CommonResult handlerException(BadHttpRequestException exception){
        return CommonResult.exception(exception.getCode(),exception.getMessage());
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public CommonResult handlerException(MethodArgumentTypeMismatchException exception){
        return CommonResult.exception(0,exception.getName()+"格式错误！");
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public CommonResult handlerException(SQLIntegrityConstraintViolationException exception){

        return CommonResult.exception(0,exception.getMessage());
    }
}
