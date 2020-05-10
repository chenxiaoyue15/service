package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
import com.hzu.community.api.dto.QuestionDTO;
import com.hzu.community.api.model.Company;
import com.hzu.community.api.model.Project;
import com.hzu.community.api.model.Question;
import com.hzu.community.api.model.User;
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
     *
     * @param
     * @param
     * @param offset
     * @param size
     * @return
     */
    public List<Question> getQuestions(Integer offset, Integer size) {
        RestResponse<List<Question>> resp = Rests.exc(() -> {


            String url = Rests.toUrl(adminServiceName, "/backstage/questionlist?offset=" + offset + "&size=" + size);
            ResponseEntity<RestResponse<List<Question>>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<List<Question>>>() {
            });
            return responseEntity.getBody();


        });
        return resp.getResult();

    }


    public Integer questioncount() {


        RestResponse<Integer> resp = Rests.exc(() -> {

            {
                String url = Rests.toUrl(adminServiceName, "/backstage/questioncount");
                ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {
                });
                return responseEntity.getBody();
            }

        });
        return resp.getResult();

    }

    public Integer usercount() {

        RestResponse<Integer> resp = Rests.exc(() -> {

            {
                String url = Rests.toUrl(adminServiceName, "/backstage/usercount");
                ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {
                });
                return responseEntity.getBody();
            }

        });
        return resp.getResult();

    }

    public List<User> getUsers(Integer offset, Integer size) {
        RestResponse<List<User>> resp = Rests.exc(() -> {


            String url = Rests.toUrl(adminServiceName, "/backstage/userlist?offset=" + offset + "&size=" + size);
            ResponseEntity<RestResponse<List<User>>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<List<User>>>() {
            });
            return responseEntity.getBody();


        });
        return resp.getResult();
    }

    public Integer companycount() {

        RestResponse<Integer> resp = Rests.exc(() -> {

            {
                String url = Rests.toUrl(adminServiceName, "/backstage/companycount");
                ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {
                });
                return responseEntity.getBody();
            }

        });
        return resp.getResult();
    }

    public List<Company> companyUsers(Integer offset, Integer size) {
        RestResponse<List<Company>> resp = Rests.exc(() -> {


            String url = Rests.toUrl(adminServiceName, "/backstage/companylist?offset=" + offset + "&size=" + size);
            ResponseEntity<RestResponse<List<Company>>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<List<Company>>>() {
            });
            return responseEntity.getBody();


        });
        return resp.getResult();
    }

    public Integer projectcount() {
        RestResponse<Integer> resp = Rests.exc(() -> {

            {
                String url = Rests.toUrl(adminServiceName, "/backstage/projectcount");
                ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {
                });
                return responseEntity.getBody();
            }

        });
        return resp.getResult();

    }

    public List<Project> projectlist(Integer offset, Integer size) {
        RestResponse<List<Project>> resp = Rests.exc(() -> {


            String url = Rests.toUrl(adminServiceName, "/backstage/projectlist?offset=" + offset + "&size=" + size);
            ResponseEntity<RestResponse<List<Project>>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<List<Project>>>() {
            });
            return responseEntity.getBody();


        });
        return resp.getResult();
    }
}