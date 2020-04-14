package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
import com.hzu.community.api.model.Question;
import com.hzu.community.api.model.QuestionDTO;
import com.hzu.community.api.model.QuestionQueryDTO;
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
     * @param
     * @param
     * @param offset
     * @param size
     * @param search
     */
    public List<Question> getQuestions(Integer offset,Integer size,String search,String tag) {
        RestResponse<List<Question>> resp = Rests.exc(() -> {

            if (search==null&&tag==null){
                String url = Rests.toUrl(userServiceName, "/question/list?offset="+offset+"&size="+size);
                ResponseEntity<RestResponse<List<Question>>> responseEntity = rest.get(url,new ParameterizedTypeReference<RestResponse<List<Question>>>() {});
                return responseEntity.getBody();
            }
            else if (search!=null&&tag==null){
            String url = Rests.toUrl(userServiceName, "/question/list?offset="+offset+"&size="+size+"&search="+search);
            ResponseEntity<RestResponse<List<Question>>> responseEntity = rest.get(url,new ParameterizedTypeReference<RestResponse<List<Question>>>() {});
            return responseEntity.getBody();
            }
            else {
                String url = Rests.toUrl(userServiceName, "/question/list?offset="+offset+"&size="+size+"&tag="+tag);
                ResponseEntity<RestResponse<List<Question>>> responseEntity = rest.get(url,new ParameterizedTypeReference<RestResponse<List<Question>>>() {});
                return responseEntity.getBody();
            }

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

    public Question getQuestion(Integer id) {
        RestResponse<Question> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/question/one?id=" + id);
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

    public List<Question> selectMyQuestion(Integer id, Integer offset, Integer size) {
        RestResponse<List<Question>> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/question/selectMyQuestion?id="+id+"&offset="+offset+"&size="+size);
            ResponseEntity<RestResponse<List<Question>>> responseEntity = rest.get(url,  new ParameterizedTypeReference<RestResponse<List<Question>>>() {
            });
            return responseEntity.getBody();
        });
        return resp.getResult();
    }

    public Integer count(String search, String tag) {


        RestResponse<Integer> resp = Rests.exc(() -> {

            if (search == null && tag ==null){
                String url = Rests.toUrl(userServiceName, "/question/count");
                ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {});
                return responseEntity.getBody();
            }else if (search!=null &&tag ==null){
            String url = Rests.toUrl(userServiceName, "/question/count?search="+search);
            ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {});
            return responseEntity.getBody();
            }else {
                String url = Rests.toUrl(userServiceName, "/question/count?tag="+tag);
                ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {});
                return responseEntity.getBody();
            }

        });return resp.getResult();
    }


    public Integer countByUserId(Integer userId) {
        RestResponse<Integer> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/question/count?creator="+userId);
            ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {});
            return responseEntity.getBody();

        });return resp.getResult();
    }

    public void updateViewCount(Question updateQuestion) {
        Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/question/updateViewCount");
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url, updateQuestion, new ParameterizedTypeReference<RestResponse<Object>>() {
            });
            return responseEntity.getBody();
        });
    }
}
