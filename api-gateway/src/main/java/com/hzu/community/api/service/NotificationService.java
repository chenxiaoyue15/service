package com.hzu.community.api.service;

import com.hzu.community.api.dao.NotificationDao;
import com.hzu.community.api.enums.NotificationStatusEnum;
import com.hzu.community.api.enums.NotificationTypeEnum;
import com.hzu.community.api.model.Notification;
import com.hzu.community.api.model.NotificationDTO;
import com.hzu.community.api.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationDao notificationDao;

//    public PaginationDTO list(Integer userId, Integer page, Integer size) {
//        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO();
//        Integer totalCount=notificationMapper.countByUserId(userId);
//        paginationDTO.setPagination(totalCount,page,size);
//        Integer offset = size * (page - 1);
//
//        List<Notification> notifications = notificationMapper.listByUserId(userId,offset,size);
//
//        if (notifications.size()==0){
//            return paginationDTO;
//        }
//        List<NotificationDTO>notificationDTOS=new ArrayList<>();
//        for (Notification notification:notifications){
//            NotificationDTO notificationDTO = new NotificationDTO();
//            BeanUtils.copyProperties(notification,notificationDTO);
//            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
//            notificationDTOS.add(notificationDTO);
//        }
//        paginationDTO.setData(notificationDTOS);
//        return paginationDTO;
//
//
//
//    }

    public Integer unreadCount(Integer userId) {

        return notificationDao.countById(userId,NotificationStatusEnum.UNREAD.getStatus());
    }

    public NotificationDTO read(Integer id, User user) {

        Notification notification = notificationDao.selectById(id);
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationDao.updateById(notification);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }

    public List<NotificationDTO> select(Integer id) {
        List<Notification> notifications = notificationDao.select(id);
        List<NotificationDTO>notificationDTOS=new ArrayList<>();
        for (Notification notification:notifications){
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        return notificationDTOS;
    }
}
