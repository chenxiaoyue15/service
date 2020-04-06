package com.hzu.community.api.service;

import com.hzu.community.api.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public String getusername(int id){
        return userDao.getusername(id);
    }
}
