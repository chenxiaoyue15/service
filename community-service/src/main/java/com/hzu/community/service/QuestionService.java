package com.hzu.community.service;


import com.hzu.community.common.RestResponse;
import com.hzu.community.mapper.QuestionMapper;
import com.hzu.community.model.Question;
import com.hzu.community.model.QuestionReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionService {
@Autowired
private QuestionMapper questionMapper;

    public List<Question> getQuestions() {

        List<Question> questions = questionMapper.selectQuestion();
        return questions;
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
}
