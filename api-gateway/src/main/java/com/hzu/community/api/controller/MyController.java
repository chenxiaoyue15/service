package com.hzu.community.api.controller;

import com.hzu.community.api.dao.UserDao;
import com.hzu.community.api.dto.PaginationDTO;
import com.hzu.community.api.dto.ResultDTO;
import com.hzu.community.api.model.HotTagCache;
import com.hzu.community.api.model.Question;
import com.hzu.community.api.model.User;
import com.hzu.community.api.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class MyController {
    private static final String TOKEN_COOKIE = "token";
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserDao userDao;
    @RequestMapping("/personal")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        HttpServletRequest request
                        ){
        Cookie cookie = WebUtils.getCookie(request, TOKEN_COOKIE);
        User user = userDao.getUserByToken(cookie.getValue());
        PaginationDTO paginationDTO = questionService.selectMyQuestion(user.getId(),page, size);
        model.addAttribute("section", "questions");
        model.addAttribute("sectionName", "我的提问");
        model.addAttribute("pagination",paginationDTO);
        return "my";
    }

    @ResponseBody
    @RequestMapping(value = "/deleted", method = RequestMethod.POST)
    public Object delete(@RequestBody Question question) {

        questionService.deleteById(question);

        return ResultDTO.okOf();
    }



}
