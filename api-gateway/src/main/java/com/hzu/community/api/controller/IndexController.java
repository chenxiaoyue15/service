package com.hzu.community.api.controller;

import com.hzu.community.api.model.Question;
import com.hzu.community.api.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/")
    public String index(Model model){
        List<Question> questions = questionService.getQuestions();
        model.addAttribute("question", questions);
        return "index";
    }





}
