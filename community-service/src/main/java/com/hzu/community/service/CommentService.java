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


    public Comment queryOneComment(Integer id) {
        Comment query = new Comment();
        query.setId(id);
        List<Comment> comments=commentMapper.queryOneComment(query);
        if (!comments.isEmpty()){
            return comments.get(0);
        }
        return null;
    }

    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }

    public void updateCommentCount(Comment comment) {
        commentMapper.updateCommentCount(comment);
    }
}
