package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
import com.hzu.community.api.model.Great;
import com.hzu.community.api.utils.Rests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GreatDao {
    @Autowired
    private GenericRest rest;
    @Value("${community.service.name}")
    private  String userServiceName;
    public List<Great> findByAidAndUid(Integer aid, Integer uid) {
        RestResponse<List<Great>> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/comment/liked?aid="+aid+"&uid="+uid);
            ResponseEntity<RestResponse<List<Great>>> responseEntity = rest.get(url,new ParameterizedTypeReference<RestResponse<List<Great>>>() {});
            return responseEntity.getBody();
        });
        return resp.getResult();
    }

    public void delete(Great great) {
        Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/comment/deleteLiked");
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url, great, new ParameterizedTypeReference<RestResponse<Object>>() {
            });
            return responseEntity.getBody();
        });
    }

    public void saveAndFlush(Great great) {
        Rests.exc(()  ->{
                String url = Rests.toUrl(userServiceName, "/comment/addLiked" );
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url,great,new ParameterizedTypeReference<RestResponse<Object>>() {});
            return responseEntity.getBody();
        });
    }
}
