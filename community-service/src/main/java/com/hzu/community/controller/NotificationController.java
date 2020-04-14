package com.hzu.community.controller;

import com.hzu.community.common.RestResponse;
import com.hzu.community.mapper.NotificationMapper;
import com.hzu.community.model.Notification;
import com.hzu.community.model.Question;
import com.hzu.community.service.NotificationService;
import com.hzu.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("notification")
@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("one")
    public RestResponse<Notification> one(Integer id){
        Notification notification = notificationService.queryId(id);
        return RestResponse.success(notification);
    }

    /**
     * 添加文章接口
     * @param
     * @return
     */
    @RequestMapping(value="add")
    public RestResponse<Object> insert(@RequestBody Notification notification){
        notificationService.addNotification(notification);

        return RestResponse.success();
    }

    @RequestMapping(value="countUnread")
    public RestResponse<Integer> countUnread(Integer userId, int status){

       Integer count =  notificationService.countUnread(userId,status);

        return RestResponse.success(count);
    }
    @RequestMapping(value="count")
    public RestResponse<Integer> count(Integer id){

        Integer count =  notificationService.count(id);

        return RestResponse.success(count);
    }
    @RequestMapping(value="updateRead")
    public RestResponse<Object> updateRead(@RequestBody Notification notification){

        notificationService.updateRead(notification);

        return RestResponse.success();
    }
    @RequestMapping("select")
    public RestResponse<List<Notification>> selectMyQuestion(Integer id,Integer offset, Integer size){
        List<Notification> list = null;
        list = notificationService.select(id,offset,size);
        return RestResponse.success(list);
    }
}
