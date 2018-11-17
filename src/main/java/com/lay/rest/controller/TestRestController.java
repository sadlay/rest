package com.lay.rest.controller;

import com.lay.rest.entity.User;
import com.lay.rest.vo.UserVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 23:32 2018/11/17
 * @Modified By:IntelliJ IDEA
 */
@RestController
public class TestRestController {

    //在restController下映射视图
    @GetMapping("/restful2")
    public ModelAndView index(){
        ModelAndView mv=new ModelAndView("restful");
        return mv;
    }


}
