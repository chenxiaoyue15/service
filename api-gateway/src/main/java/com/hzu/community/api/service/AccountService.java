package com.hzu.community.api.service;
import com.hzu.community.api.dao.UserDao;
import com.hzu.community.api.model.User;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;


/**
 * 用户登录，注册，个人信息服务
 * 
 */
@Service
public class AccountService {

  @Value("${domain.name}")
  private String domainName;

  @Autowired
  private UserDao userDao;
  
  
  


  /**
   * 校验用户名密码并返回用户对象
   * @param username
   * @param password
   * @return
   */
  public User auth(String username, String password) {
    if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
       return null;
    }
    User user = new User();
    user.setName(username);
    user.setPwd(password);
    try {
      user = userDao.authUser(user);
    } catch (Exception e) {
      return null;
    }
    return user;
  }

 
  
  
}
