package com.hzu.community.controller;

import com.hzu.community.common.RestResponse;
import com.hzu.community.model.Comment;
import com.hzu.community.model.Great;
import com.hzu.community.model.Question;
import com.hzu.community.service.CommentService;
import com.hzu.community.service.GreatService;
import com.hzu.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("comment")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private GreatService greatService;
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
    @RequestMapping("one")
    public RestResponse<Comment> one(Integer id){
        Comment comment = commentService.queryOneComment(id);
        return RestResponse.success(comment);
    }
    @RequestMapping(value="add")
    public RestResponse<Object> addComment(@RequestBody Comment comment){
        commentService.addComment(comment);

        return RestResponse.success();
    }
    @RequestMapping(value="updateCommentCount")
    public RestResponse<Object> updateCommentCount(@RequestBody Comment comment){

        commentService.updateCommentCount(comment);

        return RestResponse.success();
    }
    @RequestMapping("liked")
    public RestResponse<List<Great>> liked(Integer aid, Integer uid){
        List<Great> list = null;
        list = greatService.findByAidAndUid(aid,uid);
        return RestResponse.success(list);
    }
    @RequestMapping(value="deleteLiked")
    public RestResponse<Object> deleteLiked(@RequestBody Great great){

        greatService.deleteLiked(great);

        return RestResponse.success();
    }

    @RequestMapping(value="updateLikeCount")
    public RestResponse<Object> updateLikeCount(@RequestBody Comment comment){

        commentService.updateLikeCount(comment);

        return RestResponse.success();
    }
    @RequestMapping(value="addLiked")
    public RestResponse<Object> addLiked(@RequestBody Great great){
        greatService.addLiked(great);

        return RestResponse.success();
    }
}
