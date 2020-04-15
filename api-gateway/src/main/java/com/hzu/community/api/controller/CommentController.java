package com.hzu.community.api.controller;

import com.hzu.community.api.dao.UserDao;
import com.hzu.community.api.enums.CommentTypeEnum;
import com.hzu.community.api.model.*;
import com.hzu.community.api.service.CommentService;
import com.hzu.community.api.service.GreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    private static final String TOKEN_COOKIE = "token";
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommentService commentService;
    @Autowired
    private GreatService greatService;
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(2002, "未登录,请先登录");
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setCommentCount(0);
        comment.setLikeCount(0);
        commentService.insert(comment, user);
        return ResultDTO.okOf();

    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Integer id) {
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }

    @ResponseBody
    @RequestMapping(value = "/great", method = RequestMethod.POST)
    public Object great(@RequestBody Great great, HttpServletRequest request){
        //查询是否有该用户对该文章的点赞记录
        Cookie cookie = WebUtils.getCookie(request, TOKEN_COOKIE);
        User user =  userDao.getUserByToken(cookie.getValue());
        if (user == null) {
            return ResultDTO.errorOf(2002, "未登录,请先登录");
        }
        Integer uid = user.getId();
        Integer aid = great.getAid();
        List<Great> list=greatService.findByAidAndUid(aid,uid);
        if (list!=null && list.size()>0){
            //如果找到了这条记录，则删除该记录，同时文章的点赞数减1
             great=list.get(0);
            //删除记录
            greatService.delete(great);
            //文章点赞数减1
            Comment comment =commentService.findByIdForUpdate(aid);
            comment.setLikeCount(comment.getLikeCount()-1);
            comment.setId(comment.getId());
            commentService.saveAndFlush(comment);


        }else {
            //如果没有找到这条记录，则添加这条记录，同时文章点赞数加1
            great=new Great();
            great.setAid(aid);
            great.setUid(uid);
            //添加记录
            greatService.saveAndFlush(great);
            //文章点赞数加1
            Comment comment=commentService.findByIdForUpdate(aid);
            comment.setLikeCount(comment.getLikeCount()+1);
            commentService.saveAndFlush(comment);

        }
        return ResultDTO.okOf();

    }
}
