package com.hzu.community.api.controller;

import com.hzu.community.api.common.ResultMsg;
import com.hzu.community.api.common.UserContext;
import com.hzu.community.api.model.User;
import com.hzu.community.api.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;

@Controller

public class UserController {

    @Autowired
     private AccountService accountService;

    //----------------------------登录流程-------------------------------------------


    /**
     * 登陆
     * @param req
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }
    @RequestMapping(value="/loginsuc",method={RequestMethod.POST,RequestMethod.GET})
    public String loginSubmit(HttpServletRequest request){
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        if (username == null || password == null) {
//            request.setAttribute("target", request.getParameter("target"));
            return "login";
        }
        User user =  accountService.auth(username, password);
        if (user == null) {
            return "redirect:/login?" + "username=" + username + "&" + ResultMsg.errorMsg("用户名或密码错误").asUrlParams();
        }else {
            UserContext.setUser(user);
            return  StringUtils.isNotBlank(request.getParameter("target")) ? "redirect:" + request.getParameter("target") : "redirect:/";
        }
    }

}
