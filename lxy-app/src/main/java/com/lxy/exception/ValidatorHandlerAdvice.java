//package com.lxy.exception;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import com.lxy.entity.JsonResult;
/**
 * 统一处理参数验证异常类(已经被UnifyExceptionHandler.java代替)
 * @author Administrator
 *
 */
//@ControllerAdvice
//public class ValidatorHandlerAdvice extends ResponseEntityExceptionHandler {
//	final static private String FAILED_CODE="10000";
//    @Override
//    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        String message=ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
//        return new ResponseEntity<Object>( new JsonResult<>(FAILED_CODE,message), status);
//    }
//
//    
//    
//}
