package com.hzu.community.api.controller;

import com.hzu.community.api.enums.CommentTypeEnum;
import com.hzu.community.api.exception.CustomizeErrorCode;
import com.hzu.community.api.exception.CustomizeException;
import com.hzu.community.api.dto.CommentDTO;
import com.hzu.community.api.model.Question;
import com.hzu.community.api.dto.QuestionDTO;
import com.hzu.community.api.dto.ResultDTO;
import com.hzu.community.api.service.CommentService;
import com.hzu.community.api.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model){
        try {
            QuestionDTO questionDTO = questionService.getById(id);//把数据库里id等于id的数据传给questionDTO

        } catch (NullPointerException e) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = questionService.getById(id);//把数据库里id等于id的数据传给questionDTO
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTargetId(id,CommentTypeEnum.QUESTION);
//        System.out.println(comments);
        questionService.incView(id);//累加阅读数，用incView方法更新数据库里的数据
        model.addAttribute("question",questionDTO);//回显数据到页面
        model.addAttribute("comments",comments);//回显数据到页面
        model.addAttribute("relatedQuestions",relatedQuestions);//回显数据到页面

        return "question";


    }
//    @ResponseBody
//    @RequestMapping(value = "/deleted", method = RequestMethod.POST)
//    public Object delete(@RequestBody Question question) {
//
//         questionService.deleteById(question);
//
//        return ResultDTO.okOf();
//    }l

}
