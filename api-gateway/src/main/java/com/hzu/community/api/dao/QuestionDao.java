package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
import com.hzu.community.api.model.Question;
import com.hzu.community.api.dto.QuestionDTO;
import com.hzu.community.api.utils.Rests;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@DefaultProperties(groupKey="questionDao",
        commandProperties={@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000"),@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"),@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="4")},
        threadPoolProperties={@HystrixProperty(name="coreSize",value="2")
                ,@HystrixProperty(name="maxQueueSize",value="2")},
        threadPoolKey="questionDao"
)
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


    @HystrixCommand
    public List<Question> getQuestions(Integer offset,Integer size,String search,String tag,String sort) {
        RestResponse<List<Question>> resp = Rests.exc(() -> {
            if (sort!=null){
                String url = Rests.toUrl(userServiceName, "/question/list?offset="+offset+"&size="+size+"&sort="+sort);
                ResponseEntity<RestResponse<List<Question>>> responseEntity = rest.get(url,new ParameterizedTypeReference<RestResponse<List<Question>>>() {});
                return responseEntity.getBody();
            }
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

    @HystrixCommand
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
    @HystrixCommand
    public void addQuestion(Question question) {
        Rests.exc(()  ->{
            String url = Rests.toUrl(userServiceName, "/question/add" );
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url,question,new ParameterizedTypeReference<RestResponse<Object>>() {});
            return responseEntity.getBody();
        });
    }
    @HystrixCommand
    public void update(Question question) {
        Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/question/update");
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url, question, new ParameterizedTypeReference<RestResponse<Object>>() {
            });
            return responseEntity.getBody();
        });
    }
    @HystrixCommand
    public List<Question> selectRelated(Question question) {
        RestResponse<List<Question>> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/question/selectRelated");
            ResponseEntity<RestResponse<List<Question>>> responseEntity = rest.post(url,question,new ParameterizedTypeReference<RestResponse<List<Question>>>() {});
            return responseEntity.getBody();
        });
        return resp.getResult();
    }
    @HystrixCommand
    public Question getQuestion(Integer id) {
        RestResponse<Question> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/question/one?id=" + id);
            ResponseEntity<RestResponse<Question>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Question>>() {});
            return responseEntity.getBody();

        });return resp.getResult();
    }
    @HystrixCommand
    public void updateCommentCount(Question question) {
        Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/question/updateCommentCount");
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url, question, new ParameterizedTypeReference<RestResponse<Object>>() {
            });
            return responseEntity.getBody();
        });
    }
    @HystrixCommand
    public List<Question> selectMyQuestion(Integer id, Integer offset, Integer size) {
        RestResponse<List<Question>> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/question/selectMyQuestion?id="+id+"&offset="+offset+"&size="+size);
            ResponseEntity<RestResponse<List<Question>>> responseEntity = rest.get(url,  new ParameterizedTypeReference<RestResponse<List<Question>>>() {
            });
            return responseEntity.getBody();
        });
        return resp.getResult();
    }
    @HystrixCommand
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

    @HystrixCommand
    public Integer countByUserId(Integer userId) {
        RestResponse<Integer> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/question/count?creator="+userId);
            ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {});
            return responseEntity.getBody();

        });return resp.getResult();
    }
    @HystrixCommand
    public void updateViewCount(Question updateQuestion) {
        Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/question/updateViewCount");
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url, updateQuestion, new ParameterizedTypeReference<RestResponse<Object>>() {
            });
            return responseEntity.getBody();
        });
    }
    @HystrixCommand
    public void deleteById(Question question) {
        Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/question/deleteById");
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url, question, new ParameterizedTypeReference<RestResponse<Object>>() {
            });
            return responseEntity.getBody();
        });
    }
}
