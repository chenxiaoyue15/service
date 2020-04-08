package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
import com.hzu.community.api.model.Question;
import com.hzu.community.api.utils.Rests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class QuestionDao {
    @Autowired
    private GenericRest rest;
    @Value("${community.service.name}")
    private String userServiceName;
    public  List<Question> getQuestions() {
        RestResponse<List<Question>> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/question/list");
            ResponseEntity<RestResponse<List<Question>>> responseEntity = rest.get(url,new ParameterizedTypeReference<RestResponse<List<Question>>>() {});
            return responseEntity.getBody();
        });
        return resp.getResult();

    }
}
