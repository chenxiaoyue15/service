package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private GenericRest rest;
    public String getusername(int id){
        String url="http://community/getusername?id="+id;
        RestResponse<String> response =rest.get(url, new ParameterizedTypeReference<RestResponse<String>>() {}).getBody();
        return response.getResult();
    }
}
