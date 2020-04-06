package com.hzu.community.api.controller;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiUserController {
    @Autowired
    private UserService userService;
    @RequestMapping("getusername")
    public RestResponse<String> getusername(int id){
        return RestResponse.success(userService.getusername(id));
    }

}
