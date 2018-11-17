package com.lay.rest.controller;

import com.lay.rest.entity.User;
import com.lay.rest.entity.enumeration.SexEnum;
import com.lay.rest.service.UserService;
import com.lay.rest.vo.UserVo;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 14:58 2018/11/17
 * @Modified By:IntelliJ IDEA
 */
@Controller
public class UserController {

    //用户服务接口
    @Autowired
    private UserService userService = null;

    //映射视图
    @GetMapping("/restful")
    public String index() {
        return "/restful";
    }

    //转换Vo变Entity
    private User changeToEntity(UserVo userVo) {
        User user = new User();
        user.setId(userVo.getId());
        user.setUserName(userVo.getUserName());
        user.setSex(SexEnum.getEnumById(userVo.getSexCode()));
        user.setNote(userVo.getNote());
        return user;
    }

    //转换Entity变Vo
    private UserVo changeToVo(User user) {
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUserName(user.getUserName());
        userVo.setSexCode(user.getSex().getId());
        userVo.setSexName(user.getSex().getName());
        userVo.setNote(user.getNote());
        return userVo;
    }

    //将Entity列表转为Vo列表
    private List<UserVo> changeToVoes(List<User> userList) {
        List<UserVo> volist = new ArrayList<>();
        for (User user : userList) {
            UserVo userVo = changeToVo(user);
            volist.add(userVo);
        }
        return volist;
    }

    //将Vo列表转为Entity列表
    private List<User> changeToEntities(List<UserVo> userVoList) {
        List<User> userlist = new ArrayList<>();
        for (UserVo userVo : userVoList) {
            User user = changeToEntity(userVo);
            userlist.add(user);
        }
        return userlist;
    }

    //结果Vo
    public class ResultVo {
        private Boolean success = null;
        private String message = null;

        public ResultVo() {

        }

        public ResultVo(Boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    //测试post请求， 新增用户
    @PostMapping("/user")
    @ResponseBody
    public User insertUser(@RequestBody UserVo userVo) {
        User user = this.changeToEntity(userVo);
        return userService.inserUser(user);
    }

    //测试get请求 获取用户
    @GetMapping(value = "/user/{id}")
    @ResponseBody
    public UserVo getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return changeToVo(user);
    }

    //查询符合要求的用户
    @GetMapping("/users/{userName}/{note}/{start}/{limit}")
    @ResponseBody
    public List<UserVo> findUsers(
            @PathVariable("userName") String userName,
            @PathVariable("note") String note,
            @PathVariable("start") int start,
            @PathVariable("limit") int limit
    ) {
        List<User> userList = userService.findUsers(userName, note, start, limit);
        return this.changeToVoes(userList);
    }

    //使用HTTP的put请求修改用户信息
    @PutMapping("/user/{id}")
    @ResponseBody
    public User updateUser(@PathVariable("id") Long id, @RequestBody UserVo userVo) {
        User user = this.changeToEntity(userVo);
        user.setId(id);
        userService.updateUser(user);
        return user;
    }

    //使用PATCH请求修改用户名称
    @PatchMapping("/user/{id}/{userName}")
    @ResponseBody
    public ResultVo changeUserName(@PathVariable("id") Long id, @PathVariable("userName") String userName) {
        int result = userService.updateUserName(id, userName);
        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新用户【" + id + "】失败");
        return resultVo;
    }

    //使用HTTP的DELETE请求
    @DeleteMapping("/user/{id}")
    @ResponseBody
    public ResultVo deleteUser(@PathVariable("id") Long id) {
        int result = userService.deleteUser(id);
        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新用户【" + id + "】失败");
        return resultVo;
    }

    // 修改用户名称表单
    @PatchMapping("/user/name")
    @ResponseBody
    public ResultVo changeUserName2( Long id, String userName) {
        int result = userService.updateUserName(id, userName);
        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新用户【" + id + "】失败");
        return resultVo;
    }

    //映射视图
    @GetMapping("/user/name")
    public String changeUserName(){
        return "change_user_name";
    }
}
