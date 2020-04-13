package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
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
public class QuestionDao {
    @Autowired
    private GenericRest rest;
    @Value("${community.service.name}")
    private String userServiceName;

    /**
     * 调用文章列表接口
     * @return
     */
    public  List<Question> getQuestions() {
        RestResponse<List<Question>> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/question/list");
            ResponseEntity<RestResponse<List<Question>>> responseEntity = rest.get(url,new ParameterizedTypeReference<RestResponse<List<Question>>>() {});
            return responseEntity.getBody();
        });
        return resp.getResult();

    }

    /**
     * 调用文章详情接口
     * @param id
     * @return
     */
    public QuestionDTO getById(Integer id) {
        RestResponse<QuestionDTO> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/question/one?id=" + id);
            ResponseEntity<RestResponse<QuestionDTO>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<QuestionDTO>>() {});
            return responseEntity.getBody();

        });return resp.getResult();
    }

    /**
     * 调用添加文章接口
     * @param question
     */
    public void addQuestion(Question question) {
        Rests.exc(()  ->{
            String url = Rests.toUrl(userServiceName, "/question/add" );
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url,question,new ParameterizedTypeReference<RestResponse<Object>>() {});
            return responseEntity.getBody();
        });
    }

    public void update(Question question) {
        Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/question/update");
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url, question, new ParameterizedTypeReference<RestResponse<Object>>() {
            });
            return responseEntity.getBody();
        });
    }

    public List<Question> selectRelated(Question question) {
        RestResponse<List<Question>> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/question/selectRelated");
            ResponseEntity<RestResponse<List<Question>>> responseEntity = rest.post(url,question,new ParameterizedTypeReference<RestResponse<List<Question>>>() {});
            return responseEntity.getBody();
        });
        return resp.getResult();
    }

    public Question getQuestion(Integer parentId) {
        RestResponse<Question> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/question/one?id=" + parentId);
            ResponseEntity<RestResponse<Question>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Question>>() {});
            return responseEntity.getBody();

        });return resp.getResult();
    }

    public void updateCommentCount(Question question) {
        Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/question/updateCommentCount");
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url, question, new ParameterizedTypeReference<RestResponse<Object>>() {
            });
            return responseEntity.getBody();
        });
    }

    public List<Question> selectMyQuestion(Integer id) {
        RestResponse<List<Question>> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/question/selectMyQuestion?id="+id);
            ResponseEntity<RestResponse<List<Question>>> responseEntity = rest.get(url,  new ParameterizedTypeReference<RestResponse<List<Question>>>() {
            });
            return responseEntity.getBody();
        });
        return resp.getResult();
    }
}
