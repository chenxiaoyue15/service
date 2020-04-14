package com.hzu.community.mapper;

import com.hzu.community.model.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NotificationMapper {

    List<Notification> queryId(Notification query);

    void updateRead(Notification notification);


    Integer countUnread(@Param("userId")Integer userId, @Param("status")int status);

    void addNotification(Notification notification);

    List<Notification> select(@Param("id")Integer id, @Param(value = "offset")Integer offset, @Param(value = "size")Integer size);

    Integer count(@Param("id")Integer id);
}
