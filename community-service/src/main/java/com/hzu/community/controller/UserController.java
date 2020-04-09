package com.hzu.community.controller;

import com.hzu.community.common.RestResponse;


import com.hzu.community.model.User;
import com.hzu.community.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger LOGGER =LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
//    查询
    @RequestMapping("getById")
    public RestResponse<User> getUserById(Integer id){
       User user= userService.getUserById(id);
            return RestResponse.success(user);
    }
//------------------------登录/鉴权，使用JWT技术（JSON WEB TOKEN）--------------------------

    /**
     * 登陆
     * @param user
     * @return
     */
    @RequestMapping("auth")
    public RestResponse<User> auth(@RequestBody User user){
        User finalUser = userService.auth(user.getName(),user.getPwd());
        return RestResponse.success(finalUser);
    }


    /**
     * 鉴权
     * @param token
     * @return
     */
    @RequestMapping("get")
    public RestResponse<User> getUser(String token){
        User finalUser = userService.getLoginedUserByToken(token);
        return RestResponse.success(finalUser);
    }

}
