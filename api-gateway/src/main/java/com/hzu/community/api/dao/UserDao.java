package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
import com.hzu.community.api.model.User;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Value("${community.service.name}")
    private String userServiceName;
    @Autowired
    private GenericRest rest;
    public User authUser(User user) {
        String url="http://"+userServiceName + "/user/auth";
        ResponseEntity<RestResponse<User>> responseEntity = rest.post(url, user, new ParameterizedTypeReference<RestResponse<User>>(){}) ;
        RestResponse<User> response = responseEntity.getBody();
        if (response.getCode()==0){
            return response.getResult();
        }

        throw  new IllegalStateException("Can not auth user");

    }

    public User getUserByToken(String token) {

        String url = "http://" + userServiceName + "/user/get?token="+token;
        ResponseEntity<RestResponse<User>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<User>>(){}) ;
        RestResponse<User> response = responseEntity.getBody();
        if (response == null||response.getCode()!=0){
            return null;
        }
        return response.getResult();


    }
}
