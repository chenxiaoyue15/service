package com.hzu.community.config;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * RESTTEMPTER既支持直连又支持服务发现（负载均衡）的调用
 *
 */
@Service
public class GenericRest {

    @Resource
    private RestTemplate lbRestTemplate;

    @Resource
    private RestTemplate directRestTemplate;

    //用于区分是直连还是负载均衡
    private static final String directFlag = "direct://";

    public <T> ResponseEntity<T> post(String url,Object reqBody,ParameterizedTypeReference<T> responseType){
        RestTemplate template = getRestTemplate(url);
        url = url.replace(directFlag, "");
        return template.exchange(url, HttpMethod.POST,new HttpEntity(reqBody),responseType);
    }

    public <T> ResponseEntity<T> get(String url,ParameterizedTypeReference<T> responseType){
        RestTemplate template = getRestTemplate(url);
        url = url.replace(directFlag, "");
        return template.exchange(url, HttpMethod.GET,HttpEntity.EMPTY,responseType);
    }

    private RestTemplate getRestTemplate(String url) {
        if (url.contains(directFlag)) {
            return directRestTemplate;
        }else {
            return lbRestTemplate;
        }
    }

}