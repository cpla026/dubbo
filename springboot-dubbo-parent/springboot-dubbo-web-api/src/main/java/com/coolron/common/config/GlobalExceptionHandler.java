package com.coolron.common.config;

import com.coolron.common.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理 Handler
 * @ControllerAdvice  配置控制器通知
 * annotations 属性: 指定我们需要拦截的注解,一个或多个(多个加到大括号中,逗号分隔)
 */
// @RestControllerAdvice = @ResponseBody + @ControllerAdvice
@RestControllerAdvice(annotations = {RestController.class})
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 默认统一异常处理方法
     * @ExceptionHandler 注解用来配置需要拦截的异常类型, 也可以是自定义异常
     */
    @ExceptionHandler(Exception.class)
    // 此处可以指定返回的状态码 和 返回 结果说明
    // @ResponseStatus(reason = "exception",value = HttpStatus.BAD_REQUEST)
    public Object runtimeExceptionHandler(Exception e) {
        // 打印异常信息到控制台
        e.printStackTrace();
        log.error("请求出现异常,异常信息为: {}", e.getMessage());
        // 使用公共的结果类封装返回结果, 这里我指定状态码为 400
        return ApiResult.build(400, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        return ApiResult.build(400, fieldError.getDefaultMessage());
    }

    @ExceptionHandler(BindException.class)
    public ApiResult handleBindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        return ApiResult.build(400, fieldError.getDefaultMessage());
    }
}
