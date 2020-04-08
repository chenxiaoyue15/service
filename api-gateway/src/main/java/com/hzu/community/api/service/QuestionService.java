package com.hzu.community.api.service;


import com.hzu.community.api.dao.QuestionDao;
import com.hzu.community.api.model.Question;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;
    public List<Question> getQuestions() {

        return questionDao.getQuestions();
    }
}






