package com.hzu.community.controller;

import com.google.common.base.Objects;
import com.hzu.community.common.RestResponse;
import com.hzu.community.model.Comment;
import com.hzu.community.model.Question;
import com.hzu.community.model.QuestionReq;
import com.hzu.community.model.User;
import com.hzu.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("question")
@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 获取文章列表接口
     * @return
     */
    @RequestMapping("list")
    public RestResponse<List<Question>> list(){
        List<Question> list = null;
        list = questionService.getQuestions();
        return RestResponse.success(list);
    }

    /**
     * 获取文章详情接口
     * @param id
     * @return
     */
    @RequestMapping("one")
    public RestResponse<Question> one(Integer id){
        Question question = questionService.queryOneQuestion(id);
        return RestResponse.success(question);
    }

    /**
     * 添加文章接口
     * @param question
     * @return
     */
    @RequestMapping(value="add")
    public RestResponse<Object> leaveComment(@RequestBody Question question){
        questionService.addQuestion(question);

        return RestResponse.success();
    }

    @RequestMapping(value="update")
    public RestResponse<Object> updateQuestion(@RequestBody Question question){

         questionService.updateQuestion(question);

        return RestResponse.success();
    }
    @RequestMapping("selectRelated")
    public RestResponse<List<Question>> selectRelated(@RequestBody Question question){
        List<Question> list = null;
        list = questionService.selectRelated(question);
        return RestResponse.success(list);
    }
    @RequestMapping("selectMyQuestion")
    public RestResponse<List<Question>> selectMyQuestion(Integer id){
        List<Question> list = null;
        list = questionService.selectMyQuestion(id);
        return RestResponse.success(list);
    }
    @RequestMapping(value="updateCommentCount")
    public RestResponse<Object> updateCommentCount(@RequestBody Question question){

        questionService.updateCommentCount(question);

        return RestResponse.success();
    }
}
