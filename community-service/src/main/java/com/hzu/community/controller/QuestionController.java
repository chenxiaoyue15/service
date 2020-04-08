package com.hzu.community.controller;

import com.hzu.community.common.RestResponse;
import com.hzu.community.model.Question;
import com.hzu.community.model.QuestionReq;
import com.hzu.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("question")
@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("list")
    public RestResponse<List<Question>> list(){
        List<Question> list = null;
        list = questionService.getQuestions();
        return RestResponse.success(list);
    }




}
