package com.hzu.community.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.hzu.community.common.UserException;
import com.hzu.community.mapper.UserMapper;
import com.hzu.community.model.User;
import com.hzu.community.utils.JwtHelper;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;

    /**
     * 1.首先通过缓存获取
     * 2.不存在将从通过数据库获取用户对象
     * 3.将用户对象写入缓存，设置缓存时间5分钟
     * 4.返回对象
     *
     * @param id
     * @return
     */
    public User getUserById(Long id) {
        //Jedis对缓存的操作，key-value的操作
        String key = "user:" + id;
        String json = redisTemplate.opsForValue().get(key);
        User user = null;
        if (Strings.isNullOrEmpty(json)) {
            user = userMapper.selectById(id);
            String string = JSON.toJSONString(user);
            redisTemplate.opsForValue().set(key, string);
            //设置过期时间
            redisTemplate.expire(key, 4, TimeUnit.MINUTES);

        } else {
            //        反序列化为user对象
            user = JSON.parseObject(json, User.class);

        }
        return user;

    }

    public List<User> getUserByQuery(User user) {
        List<User> users = userMapper.select(user);

        return users;
    }

    /**
     * 校验用户名密码、生成token并返回用户对象
     * @param name
     * @param pwd
     * @return
     */
    public User auth(String name, String pwd) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(pwd)) {
            throw new UserException(UserException.Type.USER_AUTH_FAIL,"User Auth Fail");
        }
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);
        List<User> list =  getUserByQuery(user);
        if (!list.isEmpty()) {
            User retUser = list.get(0);
            onLogin(retUser);
            return retUser;
        }
        throw new UserException(UserException.Type.USER_AUTH_FAIL,"User Auth Fail");
    }

    private void onLogin(User user) {
        String token =  JwtHelper.genToken(ImmutableMap.of("name", user.getName(), "pwd", user.getPwd(),"ts",Instant.now().getEpochSecond()+""));
        renewToken(token,user.getName());
        user.setToken(token);

    }
    private String renewToken(String token, String name) {
        redisTemplate.opsForValue().set(name, token);
        //默认30分钟
        redisTemplate.expire(name, 30, TimeUnit.MINUTES);
        return token;
    }
    public User getLoginedUserByToken(String token) {
        Map<String, String> map = null;
        try {
            map = JwtHelper.verifyToken(token);
        } catch (Exception e) {
            throw new UserException(UserException.Type.USER_NOT_LOGIN,"User not login");
        }
        String name =  map.get("name");
        //获取key为email的过期时间
        Long expired = redisTemplate.getExpire(name);
        if (expired > 0L) {
            renewToken(token, name);
            User user = getUserByName(name);
            user.setToken(token);
            return user;
        }
        throw new UserException(UserException.Type.USER_NOT_LOGIN,"user not login");
    }

    private User getUserByName(String name) {
        User user = new User();
        user.setName(name);
        List<User> list= getUserByQuery(user);
        if (!list.isEmpty()){
            return list.get(0);
        }
        throw new UserException(UserException.Type.USER_NOT_FOUND,"User not found");

    }
}
