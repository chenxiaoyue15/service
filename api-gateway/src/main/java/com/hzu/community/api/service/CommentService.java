package com.hzu.community.api.service;

import com.hzu.community.api.dao.CommentDao;
import com.hzu.community.api.dao.UserDao;
import com.hzu.community.api.enums.CommentTypeEnum;
import com.hzu.community.api.model.Comment;
import com.hzu.community.api.model.CommentDTO;
import com.hzu.community.api.model.User;
import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {


//    public  List<CommentDTO> listByTargetId(Integer id, CommentTypeEnum type) {
//        //回显评论
//        List<Comment> comments = CommentDao.getComments(id, type.getType());
//        if (comments.size() == 0) {
//            return new ArrayList<>();
//        }
//        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
//        List<Integer> userIds = new ArrayList<>();
//        userIds.addAll(commentators);
//        ArrayList<User> users = new ArrayList<>();
//        for (int i = 0; i < commentators.size(); i++) {
//            User user = UserDao.ById(userIds.get(i));
//            users.add(user);
//
//
//        }
//
//
//        // List<User> users = userMapper.ById(userIds);
//
//        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
//        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
//            CommentDTO commentDTO = new CommentDTO();
//            BeanUtils.copyProperties(comment, commentDTO);
//            commentDTO.setUser(userMap.get(comment.getCommentator()));
//            return commentDTO;
//
//        }).collect(Collectors.toList());
//        return commentDTOS;
//    }
}
