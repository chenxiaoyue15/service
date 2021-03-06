/**
 * 
 */
package com.hzu.community.api.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzu.community.api.common.CommonConstants;
import com.hzu.community.api.common.UserContext;
import com.hzu.community.api.dao.UserDao;
import com.hzu.community.api.model.User;

import com.hzu.community.api.service.NotificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.google.common.base.Joiner;



/**
 * 用户鉴权拦截器，将（JWT用户信息）TOKEN信息存入Cookie里
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
  
  private static final String TOKEN_COOKIE = "token";
  
  
  @Autowired
  private UserDao userDao;
@Autowired
private NotificationService notificationService;
  
  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
          throws Exception {
    Map<String, String[]> map = req.getParameterMap();
    map.forEach((k,v) ->req.setAttribute(k, Joiner.on(",").join(v)));
    String requestURI = req.getRequestURI();
    if (requestURI.startsWith("/static") || requestURI.startsWith("/error")) {
      return true;
    }
    Cookie cookie = WebUtils.getCookie(req, TOKEN_COOKIE);
    if (cookie != null && StringUtils.isNoneBlank(cookie.getValue())) {
        //鉴权
        User user = userDao.getUserByToken(cookie.getValue());
        if (user != null) {
            req.getSession().setAttribute("user", user);//这一步把user信息给session，可在前端获取到user信息，获取名字到导航栏
            Integer unreadCount = notificationService.unreadCount(user.getId());
            req.getSession().setAttribute("unreadCount", unreadCount);
          req.setAttribute(CommonConstants.LOGIN_USER_ATTRIBUTE, user);
//          req.setAttribute(CommonConstants.USER_ATTRIBUTE, user);
          UserContext.setUser(user);
        }
    }
    return true;
  }
  

  @Override
  public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
          ModelAndView modelAndView) throws Exception {
    String requestURI = req.getRequestURI();
    if (requestURI.startsWith("/static") || requestURI.startsWith("/error")) {
      return ;
    }
    User user = UserContext.getUser();
    if (user != null && StringUtils.isNoneBlank(user.getToken())) {
       String token = requestURI.startsWith("logout")? "" : user.getToken();
       //token 存入Cookie中
       Cookie cookie = new Cookie(TOKEN_COOKIE, token);
       cookie.setPath("/");
       cookie.setHttpOnly(false);
       res.addCookie(cookie);
    }
    
  }
  
  

  @Override
  public void afterCompletion(HttpServletRequest req, HttpServletResponse response, Object handler, Exception ex)
          throws Exception {
    UserContext.remove();
  }
}
