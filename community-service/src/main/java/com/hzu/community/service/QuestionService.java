package com.hzu.community.service;


import com.hzu.community.mapper.QuestionMapper;
import com.hzu.community.model.Question;
import com.hzu.community.model.QuestionQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionService {
@Autowired
private QuestionMapper questionMapper;

    public List<Question> getQuestions(Integer offset, Integer size, String search, String tag, String sort) {

        return questionMapper.selectQuestion(offset,size,search,tag,sort);
    }


    public Question queryOneQuestion(Integer id) {
        Question query = new Question();
        query.setId(id);
        List<Question> questions=questionMapper.queryOneQuestion(query);
        if (!questions.isEmpty()){
            return questions.get(0);
        }
        return null;
    }

    public void addQuestion(Question question) {

        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setViewCount(0);
        question.setCommentCount(0);
        question.setLikeCount(0);
        questionMapper.insert(question);
    }

    public void updateQuestion(Question question) {
        question.setGmtModified(question.getGmtCreate());
        questionMapper.update(question);

    }

    public List<Question> selectRelated(Question question) {
        List<Question> questions = questionMapper.selectRelated(question);
        return questions;
    }

    public void updateCommentCount(Question question) {
        questionMapper.updateCommentCount(question);
    }

    public List<Question> selectMyQuestion(Integer id,Integer offset, Integer size ) {
        List<Question> questions = questionMapper.selectMyQuestion(id,offset,size);
        return questions;
    }

    public Integer count(String search, String tag) {

        return questionMapper.count(search,tag);
    }


    public void updateViewCount(Question updateQuestion) {

        questionMapper.updateViewCount(updateQuestion);
    }
}
