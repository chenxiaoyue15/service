package com.hzu.community.api.service;

import com.hzu.community.api.dao.CommentDao;
import com.hzu.community.api.dao.NotificationDao;
import com.hzu.community.api.dao.QuestionDao;
import com.hzu.community.api.dao.UserDao;
import com.hzu.community.api.dto.CommentDTO;
import com.hzu.community.api.enums.CommentTypeEnum;
import com.hzu.community.api.enums.NotificationStatusEnum;
import com.hzu.community.api.enums.NotificationTypeEnum;
import com.hzu.community.api.model.*;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private NotificationDao notificationDao;
    public  List<CommentDTO> listByTargetId(Integer id, CommentTypeEnum type) {
        //回显评论
        List<Comment> comments = commentDao.getComments(id, type.getType());
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(commentators);
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < commentators.size(); i++) {
            User user = userDao.ById(userIds.get(i));
            users.add(user);


        }


        // List<User> users = userMapper.ById(userIds);

        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;

        }).collect(Collectors.toList());
        return commentDTOS;
    }

    public void insert(Comment comment, User commentator) {
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //插入评论
            Comment dbComment = commentDao.selectById(comment.getParentId());
            Question question = questionDao.getQuestion(dbComment.getParentId());
            commentDao.insert(comment);


            //增加评论数
            Comment updateCommentCount = new Comment();
            updateCommentCount.setId(comment.getParentId());
            //updateCommentCount.setCommentCount(comment.getCommentCount()+1);
            commentDao.incCommentCount(updateCommentCount);
            //创建通知
            createNotify(comment, dbComment.getCommentator(), commentator.getAccountId(), question.getTitle(), NotificationTypeEnum.REPLY_COMMENT, question.getId());
        } else {
            //回复问题
            Question question = questionDao.getQuestion(comment.getParentId());
//            comment.setCommentCount(0);
            commentDao.insert(comment);
            question.setId(comment.getParentId());
            questionDao.updateCommentCount(question);
            //创建通知
            createNotify(comment, question.getCreator(), commentator.getAccountId(), question.getTitle(),NotificationTypeEnum.REPLY_QUESTION, question.getId());
        }
    }
    //        创建通知的方法
    private void createNotify(Comment comment, Integer receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Integer outerId) {
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setOuterId(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationDao.insert(notification);
    }

    public Comment findByIdForUpdate(int aid) {

        return commentDao.selectById(aid);
    }

    public void saveAndFlush(Comment comment) {
        commentDao.saveAndFlush(comment);
    }

    public Integer findAll() {
        return commentDao.findAll();
    }
}
