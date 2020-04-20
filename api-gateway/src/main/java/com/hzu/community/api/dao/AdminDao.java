package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
import com.hzu.community.api.dto.QuestionDTO;
import com.hzu.community.api.model.Question;
import com.hzu.community.api.utils.Rests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao {
    @Autowired
    private GenericRest rest;
    @Value("${admin.service.name}")
    private String adminServiceName;

    /**
     * 调用文章列表接口
     * @return
     * @param
     * @param
     * @param offset
     * @param size
     */
    public List<Question> getQuestions(Integer offset,Integer size) {
        RestResponse<List<Question>> resp = Rests.exc(() -> {


                String url = Rests.toUrl(adminServiceName, "/backstage/questionlist?offset="+offset+"&size="+size);
                ResponseEntity<RestResponse<List<Question>>> responseEntity = rest.get(url,new ParameterizedTypeReference<RestResponse<List<Question>>>() {});
                return responseEntity.getBody();


        });
        return resp.getResult();

    }



    public Integer count() {


        RestResponse<Integer> resp = Rests.exc(() -> {

             {
                String url = Rests.toUrl(adminServiceName, "/backstage/questioncount");
                ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {});
                return responseEntity.getBody();
            }

        });return resp.getResult();
    }



}
