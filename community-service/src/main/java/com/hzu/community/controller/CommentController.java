package com.hzu.community.controller;

import com.hzu.community.common.RestResponse;
import com.hzu.community.model.Comment;
import com.hzu.community.model.Question;
import com.hzu.community.service.CommentService;
import com.hzu.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("comment")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 获取评论接口
     * @param id
     * @param type
     * @return
     */
    @RequestMapping("list")
    public RestResponse<List<Comment>> list(Integer id,Integer type){
        List<Comment> list = null;
        list = commentService.getComment(id,type);
        return RestResponse.success(list);
    }
//    @RequestMapping("one")
//    public RestResponse<Question> one(Integer id){
//        Question question = questionService.queryOneQuestion(id);
//        return RestResponse.success(question);
//    }



}
