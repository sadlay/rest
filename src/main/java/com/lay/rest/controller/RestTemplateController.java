package com.lay.rest.controller;


import com.lay.rest.entity.User;
import com.lay.rest.vo.UserVo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:RestTemplate
 * @Author: lay
 * @Date: Created in 1:02 2018/11/18
 * @Modified By:IntelliJ IDEA
 */
@RestController
public class RestTemplateController {

    private RestTemplate restTemplate=new RestTemplate();
    //使用REST Template进行HTTP get请求
    @GetMapping("/user/byId")
    public UserVo getUser(){
        Long id=1L;
        //消费服务，第一个参数为url，第二个参数是返回类型，第三个是uri路径参数
        UserVo userVo=restTemplate.getForObject("http://localhost:8080/user/{id}",UserVo.class,id);
        System.out.println(userVo.getUserName());
        return userVo;
    }

    //Rest Template使用多参数的HTTP get请求
    @GetMapping("/users/select")
    @ResponseBody
    public List<UserVo> findUsers() {
        Map<String,Object> params=new HashMap<>();
        params.put("userName","user");
        params.put("note","note");
        params.put("start",2);
        params.put("limit",8);
        String url="http://localhost:8080/users/{userName}/{note}/{start}/{limit}";
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class, params);
        List<UserVo> userVos=responseEntity.getBody();
        return userVos;
    }

    //通过POST 请求传递JSON 请求体（BODY）
    @GetMapping("/user/insert")
    public User insertUser(){
        UserVo userVo=new UserVo();
        userVo.setUserName("蜡笔小新");
        userVo.setSexCode(1);
        userVo.setNote("喜欢吃冰淇淋");
        String url="http://localhost:8080/user";
        //请求头
        HttpHeaders headers=new HttpHeaders();
        //设置请求内容为JSON类型
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //创建请求实体对象
        HttpEntity<UserVo> request=new HttpEntity<>(userVo,headers);
        User user = restTemplate.postForObject("http://localhost:8080/user", request, User.class);
        //User user=restTemplate.postForObject(url,userVo,User.class);
        System.out.println(user.getId());
        return user;
    }

    //获取服务器响应头属性和HTTP状态码
    @GetMapping("/user/userEntity")
    public Map<String,Object> insertUserEntity(){
        UserVo userVo=new UserVo();
        userVo.setUserName("蜡笔小新");
        userVo.setSexCode(1);
        userVo.setNote("喜欢吃冰淇淋");
        String url="http://localhost:8080/user";
        //请求头
        HttpHeaders headers=new HttpHeaders();
        //设置请求内容为JSON类型
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //创建请求实体对象
        HttpEntity<UserVo> request=new HttpEntity<>(userVo,headers);
        ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://localhost:8080/user2/entity", request, User.class);
        //获取响应体
        User user=responseEntity.getBody();
        HttpHeaders respHeaders=responseEntity.getHeaders();
        //获取响应属性
        List<String> success=respHeaders.get("success");
        //响应的HTTP状态码
        int status=responseEntity.getStatusCodeValue();
        System.out.println(user.getId());
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("body",user);
        resultMap.put("responseHeaders",respHeaders);
        resultMap.put("success",success);
        resultMap.put("status",status);
        return resultMap;
    }

    //restTemplate 执行DELETE请求
    @GetMapping("/user/delete")
    public void deleteUser(){
        Long id=1L;
        restTemplate.delete("http://localhost:8080/user{id}",id);
    }
}
