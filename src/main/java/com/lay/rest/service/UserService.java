package com.lay.rest.service;

import com.lay.rest.entity.User;

import java.util.List;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 14:59 2018/11/17
 * @Modified By:IntelliJ IDEA
 */
public interface UserService {

    User inserUser(User user);

    User getUser(Long id);

    List<User> findUsers(String userName, String note, int start, int limit);

    User updateUser(User user);

    int updateUserName(Long id, String userName);

    int deleteUser(Long id);
}
