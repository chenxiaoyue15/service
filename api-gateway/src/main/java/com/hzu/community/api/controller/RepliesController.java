package com.hzu.community.api.controller;

import com.hzu.community.api.dao.UserDao;
import com.hzu.community.api.dto.PaginationDTO;
import com.hzu.community.api.model.User;
import com.hzu.community.api.service.NotificationService;
import com.hzu.community.api.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class RepliesController {
    private static final String TOKEN_COOKIE = "token";
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserDao userDao;
    @RequestMapping("/replies")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        HttpServletRequest request
                        ){
        Cookie cookie = WebUtils.getCookie(request, TOKEN_COOKIE);
        User user = userDao.getUserByToken(cookie.getValue());
        PaginationDTO paginationDTO = notificationService.select(user.getId(),page, size);
        model.addAttribute("section", "replies");
        model.addAttribute("sectionName", "最新回复");
        model.addAttribute("pagination",paginationDTO);
        return "notification";
    }





}
