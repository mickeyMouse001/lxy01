package com.lxy.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.lxy.dto.JsonResult;
import com.lxy.enums.ResultEnum;
/**
 * 统一异常处理
 * @author Administrator
 *
 */
@RestController
@ControllerAdvice
public class UnifyExceptionHandler {
    public static final String ERROR_VIEW = "error";
    final static private int FAILED_CODE=40000;
    
    @ExceptionHandler(value = Exception.class)
    public JsonResult<?> errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e){
        e.printStackTrace();
        System.out.println("其他----"+ResultEnum.OTHER_ERROR.getCode()+";"+ResultEnum.OTHER_ERROR.getMsg());
        return new JsonResult<Integer>(null,ResultEnum.OTHER_ERROR.getCode(),ResultEnum.OTHER_ERROR.getMsg());
    }
    /**
     * 参数验证异常处理
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public JsonResult<?> eHandler(HttpServletRequest request, HttpServletResponse response, BindException e){
    	String message=e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
//        e.printStackTrace();
        return new JsonResult<>(FAILED_CODE,message);
    }
    
    
    @ExceptionHandler(value = AuthException.class)
    public JsonResult<?> eHandler(HttpServletRequest request, HttpServletResponse response, AuthException e){
//        e.printStackTrace();
        return new JsonResult<>(e);
    }
    
    
}