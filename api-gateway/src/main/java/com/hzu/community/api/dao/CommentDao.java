package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
import com.hzu.community.api.model.Comment;
import com.hzu.community.api.model.Question;
import com.hzu.community.api.model.QuestionDTO;
import com.hzu.community.api.utils.Rests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDao {
    @Autowired
    private static GenericRest rest;
    @Value("${community.service.name}")
    private static String userServiceName;




    public static List<Comment> getComments(Integer id, Integer type) {

        RestResponse<List<Comment>> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/comment/list?id="+id+"&type="+type);
            ResponseEntity<RestResponse<List<Comment>>> responseEntity = rest.get(url,new ParameterizedTypeReference<RestResponse<List<Comment>>>() {});
            return responseEntity.getBody();
        });
        return resp.getResult();

    }

}