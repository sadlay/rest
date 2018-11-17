package com.lay.rest.service.impl;

import com.lay.rest.entity.User;
import com.lay.rest.entity.enumeration.SexEnum;
import com.lay.rest.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 15:01 2018/11/17
 * @Modified By:IntelliJ IDEA
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public int deleteUser(Long id) {
        return 1;
    }

    @Override
    public User inserUser(User user) {
        user.setId(1L);
        return user;
    }

    @Override
    public User getUser(Long id) {
        User user=new User();
        user.setId(id);
        user.setUserName("xiaohua");
        user.setSex(SexEnum.getEnumById(2));
        user.setNote("student");
        return user;
    }

    @Override
    public User updateUser(User user) {
        return user;
    }

    @Override
    public int updateUserName(Long id, String userName) {
        return 1;
    }

    @Override
    public List<User> findUsers(String userName, String note, int start, int limit) {
        List<User> userList=new ArrayList<>();
        for(int i=start;i<start+limit-1;i++){
            User user =new User();
            user.setId((long)i);
            user.setUserName(userName);
            user.setSex(SexEnum.getEnumById(1));
            user.setNote(note);
            userList.add(user);
        }
        return userList;
    }


}
