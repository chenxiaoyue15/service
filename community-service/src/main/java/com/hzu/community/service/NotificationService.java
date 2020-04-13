package com.hzu.community.service;


import com.hzu.community.mapper.NotificationMapper;
import com.hzu.community.mapper.QuestionMapper;
import com.hzu.community.model.Notification;
import com.hzu.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificationService {
@Autowired
private NotificationMapper notificationMapper;


    public Notification queryId(Integer id) {
        Notification query = new Notification();
        query.setId(id);
        List<Notification> notifications=notificationMapper.queryId(query);
        if (!notifications.isEmpty()){
            return notifications.get(0);
        }
        return null;

    }

    public void updateRead(Notification notification) {
        notificationMapper.updateRead(notification);
    }

    public Integer countUnread(Integer userId, int status) {
            return notificationMapper.countUnread(userId,status);
    }

    public void addNotification(Notification notification) {
        notificationMapper.addNotification(notification);
    }

    public List<Notification> select(Integer id) {
        List<Notification> notifications = notificationMapper.select(id);
        return notifications;
    }
}
