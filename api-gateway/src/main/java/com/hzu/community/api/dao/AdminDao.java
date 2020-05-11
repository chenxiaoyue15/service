package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
import com.hzu.community.api.dto.QuestionDTO;
import com.hzu.community.api.model.*;
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

    public Integer employmentcount() {

        RestResponse<Integer> resp = Rests.exc(() -> {

            {
                String url = Rests.toUrl(adminServiceName, "/backstage/employmentcount");
                ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {
                });
                return responseEntity.getBody();
            }

        });
        return resp.getResult();
    }

    public List<UserMsg> employmentlist(Integer offset, Integer size) {
        RestResponse<List<UserMsg>> resp = Rests.exc(() -> {


            String url = Rests.toUrl(adminServiceName, "/backstage/employmentlist?offset=" + offset + "&size=" + size);
            ResponseEntity<RestResponse<List<UserMsg>>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<List<UserMsg>>>() {
            });
            return responseEntity.getBody();


        });
        return resp.getResult();
    }

    public Integer HRusercount() {

        RestResponse<Integer> resp = Rests.exc(() -> {

            {
                String url = Rests.toUrl(adminServiceName, "/backstage/HRusercount");
                ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {
                });
                return responseEntity.getBody();
            }

        });
        return resp.getResult();
    }

    public List<User> getHRUsers(Integer offset, Integer size) {
        RestResponse<List<User>> resp = Rests.exc(() -> {


            String url = Rests.toUrl(adminServiceName, "/backstage/HRuserlist?offset=" + offset + "&size=" + size);
            ResponseEntity<RestResponse<List<User>>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<List<User>>>() {
            });
            return responseEntity.getBody();


        });
        return resp.getResult();
    }

    public Project projectlist1(Long projectId) {
        RestResponse<Project> resp = Rests.exc(() -> {

            String url = Rests.toUrl(adminServiceName, "/backstage/project1?projectId=" + projectId);
            ResponseEntity<RestResponse<Project>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Project>>() {});
            return responseEntity.getBody();

        });return resp.getResult();
    }

    public void deleteById(Company company) {
        Rests.exc(() -> {
            String url = Rests.toUrl(adminServiceName, "/backstage/deleteById");
            ResponseEntity<RestResponse<Company>> responseEntity = rest.post(url, company, new ParameterizedTypeReference<RestResponse<Company>>() {
            });
            return responseEntity.getBody();
        });
    }

    public void openit(User user) {
        Rests.exc(() -> {
            String url = Rests.toUrl(adminServiceName, "/backstage/openit");
            ResponseEntity<RestResponse<User>> responseEntity = rest.post(url, user, new ParameterizedTypeReference<RestResponse<User>>() {
            });
            return responseEntity.getBody();
        });
    }

    public void closeit(User user) {
        Rests.exc(() -> {
            String url = Rests.toUrl(adminServiceName, "/backstage/closeit");
            ResponseEntity<RestResponse<User>> responseEntity = rest.post(url, user, new ParameterizedTypeReference<RestResponse<User>>() {
            });
            return responseEntity.getBody();
        });
    }

    public void added(Project project) {
        Rests.exc(() -> {
            String url = Rests.toUrl(adminServiceName, "/backstage/added");
            ResponseEntity<RestResponse<User>> responseEntity = rest.post(url, project, new ParameterizedTypeReference<RestResponse<User>>() {
            });
            return responseEntity.getBody();
        });
    }
    public void out(Project project) {
        Rests.exc(() -> {
            String url = Rests.toUrl(adminServiceName, "/backstage/out");
            ResponseEntity<RestResponse<User>> responseEntity = rest.post(url, project, new ParameterizedTypeReference<RestResponse<User>>() {
            });
            return responseEntity.getBody();
        });
    }
}