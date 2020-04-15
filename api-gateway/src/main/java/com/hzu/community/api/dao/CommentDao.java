package com.hzu.community.api.dao;

import com.hzu.community.api.common.RestResponse;
import com.hzu.community.api.config.GenericRest;
import com.hzu.community.api.model.Comment;
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
    private  GenericRest rest;
    @Value("${community.service.name}")
    private  String userServiceName;


    /**
     * 调用评论列表接口
     * @param id
     * @param type
     * @return
     */

    public  List<Comment> getComments(Integer id, Integer type) {

        RestResponse<List<Comment>> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/comment/list?id="+id+"&type="+type);
            ResponseEntity<RestResponse<List<Comment>>> responseEntity = rest.get(url,new ParameterizedTypeReference<RestResponse<List<Comment>>>() {});
            return responseEntity.getBody();
        });
        return resp.getResult();

    }

    public Comment selectById(Integer parentId) {
        RestResponse<Comment> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/comment/one?id=" + parentId);
            ResponseEntity<RestResponse<Comment>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Comment>>() {});
            return responseEntity.getBody();

        });return resp.getResult();
    }

    public void insert(Comment comment) {
        Rests.exc(()  ->{
            String url = Rests.toUrl(userServiceName, "/comment/add" );
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url,comment,new ParameterizedTypeReference<RestResponse<Object>>() {});
            return responseEntity.getBody();
        });
    }

    public void incCommentCount(Comment updateCommentCount) {
        Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/comment/updateCommentCount");
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url, updateCommentCount, new ParameterizedTypeReference<RestResponse<Object>>() {
            });
            return responseEntity.getBody();
        });
    }


    public void saveAndFlush(Comment comment) {
        Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/comment/updateLikeCount");
            ResponseEntity<RestResponse<Object>> responseEntity = rest.post(url, comment, new ParameterizedTypeReference<RestResponse<Object>>() {
            });
            return responseEntity.getBody();
        });
    }

    public Integer findAll() {
        RestResponse<Integer> resp = Rests.exc(() -> {

            String url = Rests.toUrl(userServiceName, "/comment/findAllLiked");
            ResponseEntity<RestResponse<Integer>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<Integer>>() {});
            return responseEntity.getBody();

        });return resp.getResult();

    }
}
