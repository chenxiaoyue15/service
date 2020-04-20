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
}
