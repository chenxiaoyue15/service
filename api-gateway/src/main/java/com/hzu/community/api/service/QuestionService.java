package com.hzu.community.api.service;


import com.hzu.community.api.dao.QuestionDao;
import com.hzu.community.api.dao.UserDao;
import com.hzu.community.api.model.Question;


import com.hzu.community.api.model.QuestionDTO;
import com.hzu.community.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private UserDao userDao;
    public List<Question> getQuestions() {

        return questionDao.getQuestions();
    }

    public QuestionDTO getById(Integer id) {
        QuestionDTO questionDTO = questionDao.getById(id);
        User user = userDao.getUser(questionDTO.getCreator());
        questionDTO.setUser(user);
        return questionDTO;

    }
}






