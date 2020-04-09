package com.hzu.community.service;


import com.hzu.community.mapper.CommentMapper;
import com.hzu.community.mapper.QuestionMapper;
import com.hzu.community.model.Comment;
import com.hzu.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentService {
@Autowired
private CommentMapper commentMapper;

    public List<Comment> getComment(Integer id, Integer type) {

        List<Comment> Comments = commentMapper.selectComment(id,type);
        return Comments;
    }



}
