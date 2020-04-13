package com.hzu.community.api.controller;

import com.hzu.community.api.dao.UserDao;
import com.hzu.community.api.model.Notification;
import com.hzu.community.api.model.NotificationDTO;
import com.hzu.community.api.model.Question;
import com.hzu.community.api.model.User;
import com.hzu.community.api.service.NotificationService;
import com.hzu.community.api.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    private static final String TOKEN_COOKIE = "token";

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserDao userDao;
    @GetMapping("/profile/{action}")//他是一个get方法，地址是/profile，就是希望访问profile的时候来调用这个地址
    public String profile(@PathVariable(name = "action") String action,
                          Model model, HttpServletRequest request
                         ) {//定义一个方法，String的意思就是返回他对应的页面，
        Cookie cookie = WebUtils.getCookie(request, TOKEN_COOKIE);
        User user = userDao.getUserByToken(cookie.getValue());

        if ("questions".equals(action)) {
            List<Question> question = questionService.selectMyQuestion(user.getId());
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            model.addAttribute("pagination",question);

        } else if ("replies".equals(action)) {
            List<NotificationDTO> notification = notificationService.select(user.getId());
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            model.addAttribute("pagination",notification);
        }

        return "profile";

    }
}
