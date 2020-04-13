package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
import com.hzu.community.api.model.Notification;
import com.hzu.community.api.utils.Rests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationDao {
    @Autowired
    private  GenericRest rest;
    @Value("${community.service.name}")
    private  String userServiceName;


    public Notification selectById(Integer id) {
        RestResponse<Notification> resp = Rests.exc(() -> {

                String url = Rests.toUrl(userServiceName, "/notification/one?id=" + id);
                ResponseEntity<RestResponse<Notification>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Notification>>() {});
                return responseEntity.getBody();

        });return resp.getResult();
    }

    public void updateById(Notification notification) {
        Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/notification/updateRead");
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url, notification, new ParameterizedTypeReference<RestResponse<Object>>() {
            });
            return responseEntity.getBody();
        });
    }

    public Integer countById(Integer userId, int status) {

        RestResponse<Integer> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/notification/countUnread?userId="+userId+"&status="+status);
            ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {});
            return responseEntity.getBody();

        });return resp.getResult();
    }

    public void insert(Notification notification) {
        Rests.exc(()  ->{
            String url = Rests.toUrl(userServiceName, "/notification/add" );
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url,notification,new ParameterizedTypeReference<RestResponse<Object>>() {});
            return responseEntity.getBody();
        });
    }


    public List<Notification> select(Integer id) {
        RestResponse<List<Notification>> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/notification/select?id="+id);
            ResponseEntity<RestResponse<List<Notification>>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<List<Notification>>>() {
            });
            return responseEntity.getBody();
        });
        return resp.getResult();
    }

}
