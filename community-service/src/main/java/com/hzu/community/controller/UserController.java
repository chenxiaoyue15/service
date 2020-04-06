package com.hzu.community.controller;

import com.hzu.community.common.RestResponse;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    private static final Logger LOGGER =LoggerFactory.getLogger(UserController.class);

    @Value("${server.port}")
    private Integer port;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @RequestMapping("getusername")
    public RestResponse<String> getusername(Long id){
        LOGGER.info("Incoming Request,and my server port is" + port);
        redisTemplate.opsForValue().set("key1","val1");
        LOGGER.info("Test Redis:"+redisTemplate.opsForValue().get("key1"));
        return RestResponse.success("test-username"+port);
    }
}
