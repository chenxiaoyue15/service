package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
import com.hzu.community.api.model.QuestionDTO;
import com.hzu.community.api.model.User;
import com.hzu.community.api.utils.Rests;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Value("${community.service.name}")
    private  String userServiceName;
    @Autowired
    private  GenericRest rest;

    /**
     * 调用论坛服务的用户属性接口
     * @param integer
     * @return
     */
    public   User ById(Integer integer) {
        RestResponse<User> resp = Rests.exc(() -> {

            String url = "http://" + userServiceName + "/user/getById?id="+integer;
            ResponseEntity<RestResponse<User>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<User>>() {});
            return responseEntity.getBody();

        });return resp.getResult();



    }

    /**
     * 调用用户登录接口
     * @param user
     * @return
     */
    public User authUser(User user) {
        String url="http://"+userServiceName + "/user/auth";
        ResponseEntity<RestResponse<User>> responseEntity = rest.post(url, user, new ParameterizedTypeReference<RestResponse<User>>(){}) ;
        RestResponse<User> response = responseEntity.getBody();
        if (response.getCode()==0){
            return response.getResult();
        }

        throw  new IllegalStateException("Can not auth user");

    }

    /**
     * 调用用户鉴权接口
     * @param token
     * @return
     */
    public User getUserByToken(String token) {

        String url = "http://" + userServiceName + "/user/get?token="+token;
        ResponseEntity<RestResponse<User>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<User>>(){}) ;
        RestResponse<User> response = responseEntity.getBody();
        if (response == null||response.getCode()!=0){
            return null;
        }
        return response.getResult();


    }

    public  User getUser(Integer creator) {

        RestResponse<User> resp = Rests.exc(() -> {

            String url = "http://" + userServiceName + "/user/getById?id="+creator;
            ResponseEntity<RestResponse<User>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<User>>() {});
            return responseEntity.getBody();

        });return resp.getResult();

    }
}
