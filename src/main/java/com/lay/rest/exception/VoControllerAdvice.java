package com.lay.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 控制器通知
 * @Author: lay
 * @Date: Created in 0:14 2018/11/18
 * @Modified By:IntelliJ IDEA
 */

@ControllerAdvice(
        //指定拦截包的控制器
        basePackages = {"com.lay.rest.controller.*"},
        // 限定为标注为@Controller和@RestController的类才会被拦截
        annotations = {Controller.class, RestController.class})
public class VoControllerAdvice {

    //异常处理，可以定义异常类型进行拦截处理
    @ExceptionHandler(value = NotFoundException.class)
    //以json表达式响应
    @ResponseBody
    //定义服务器错误状态吗
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> exception(HttpServletRequest request,NotFoundException ex){
        Map<String,Object> msgMap=new HashMap<>();
        //获取异常信息
        msgMap.put("code",ex.getCode());
        msgMap.put("message",ex.getCustomMsg());
        return msgMap;
    }
    //异常处理，可以定义异常类型进行拦截处理
    @ExceptionHandler(value = RuntimeException.class)
    //以json表达式响应
    @ResponseBody
    //定义服务器错误状态吗
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> exceptionAll(HttpServletRequest request,Exception ex){
        Map<String,Object> msgMap=new HashMap<>();
        //获取异常信息
        msgMap.put("message",ex.getMessage());
        return msgMap;
    }
}
