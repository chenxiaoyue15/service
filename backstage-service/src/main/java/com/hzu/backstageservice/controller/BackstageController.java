package com.hzu.backstageservice.controller;

import com.hzu.backstageservice.common.RestResponse;
import com.hzu.backstageservice.model.Question;
import com.hzu.backstageservice.model.User;
import com.hzu.backstageservice.service.QuestionService;
import com.hzu.backstageservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("backstage")
@RestController
public class BackstageController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @RequestMapping("questionlist")
    public RestResponse<List<Question>> list(Integer offset, Integer size){
        List<Question> list = null;
        list = questionService.getQuestions(offset,size);
        return RestResponse.success(list);
    }
    @RequestMapping("userlist")
    public RestResponse<List<User>> users(Integer offset, Integer size){
        List<User> list = null;
        list = userService.getUsers(offset,size);
        return RestResponse.success(list);
    }
    @RequestMapping(value="questioncount")
    public RestResponse<Integer> count(){

        Integer count =  questionService.count();

        return RestResponse.success(count);
    }
}
