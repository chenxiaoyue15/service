package com.hzu.backstageservice.service;


import com.hzu.backstageservice.mappers.UserMapper;

import com.hzu.backstageservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public List<User> getUsers(Integer offset, Integer size) {
        return userMapper.selectUsers(offset,size);
    }

    public Integer count() {

        return userMapper.count();
    }

    public List<User> getHRUsers(Integer offset, Integer size) {
        return userMapper.selectHRUsers(offset,size);
    }

    public Integer HRcount() {
        return userMapper.HRcount();

    }

    public void openit(User user) {
        userMapper.openit(user);
    }

    public void closeit(User user) {
        userMapper.closeit(user);
    }
}
