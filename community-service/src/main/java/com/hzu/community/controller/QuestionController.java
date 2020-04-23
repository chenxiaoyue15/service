package com.hzu.community.controller;

import com.google.common.base.Objects;
import com.hzu.community.common.RestResponse;
import com.hzu.community.model.*;
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
    public RestResponse<List<Question>> list(Integer offset,Integer size,String search,String tag,String sort){
        List<Question> list = null;
        list = questionService.getQuestions(offset,size,search,tag,sort);
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
    public RestResponse<Object> addQuestion(@RequestBody Question question){
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
    public RestResponse<List<Question>> selectMyQuestion(Integer id,Integer offset, Integer size){
        List<Question> list = null;
        list = questionService.selectMyQuestion(id,offset,size);
        return RestResponse.success(list);
    }
    @RequestMapping(value="updateCommentCount")
    public RestResponse<Object> updateCommentCount(@RequestBody Question question){

        questionService.updateCommentCount(question);

        return RestResponse.success();
    }
    @RequestMapping(value="count")
    public RestResponse<Integer> count(String search, String tag){

        Integer count =  questionService.count(search,tag);

        return RestResponse.success(count);
    }
    @RequestMapping(value="updateViewCount")
    public RestResponse<Object> updateViewCount(@RequestBody Question updateQuestion){

        questionService.updateViewCount(updateQuestion);

        return RestResponse.success();
    }
    @RequestMapping(value="deleteById")
    public RestResponse<Object> deleteById(@RequestBody Question question){

        questionService.deleteById(question);

        return RestResponse.success();
    }
}
