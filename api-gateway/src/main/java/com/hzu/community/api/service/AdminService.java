package com.hzu.community.api.service;


import com.hzu.community.api.dao.AdminDao;
import com.hzu.community.api.dao.QuestionDao;
import com.hzu.community.api.dao.UserDao;
import com.hzu.community.api.dto.PaginationDTO;
import com.hzu.community.api.dto.QuestionDTO;
import com.hzu.community.api.model.Company;
import com.hzu.community.api.model.Project;
import com.hzu.community.api.model.Question;
import com.hzu.community.api.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UserDao userDao;
    public PaginationDTO getQuestions(Integer page, Integer size) {


        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount=adminDao.questioncount();
        paginationDTO.setPagination(totalCount,page,size);
        Integer offset = size * (page - 1);
        List<Question> questions =  adminDao.getQuestions(offset,size);
//        List<QuestionDTO>questionDTOList=new ArrayList<>();
//
//        for (Question question : questions){
//            User  user = adminDao.ById(question.getCreator());
//            QuestionDTO questionDTO = new QuestionDTO();
//            BeanUtils.copyProperties(question,questionDTO);//把第一个的所有属性拷贝到第二个
//            questionDTO.setUser(user);
//            questionDTOList.add(questionDTO);
//        }
        paginationDTO.setData(questions);





        return paginationDTO;
    }

    public PaginationDTO getusers(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount=adminDao.usercount();
        paginationDTO.setPagination(totalCount,page,size);
        Integer offset = size * (page - 1);
        List<User> users =  adminDao.getUsers(offset,size);
        paginationDTO.setData(users);
        return paginationDTO;
    }

    public PaginationDTO getcompanyusers(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount=adminDao.companycount();
        paginationDTO.setPagination(totalCount,page,size);
        Integer offset = size * (page - 1);
        List<Company> users =  adminDao.companyUsers(offset,size);
        paginationDTO.setData(users);
        return paginationDTO;
    }

    public PaginationDTO projectlist(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount=adminDao.projectcount();
        paginationDTO.setPagination(totalCount,page,size);
        Integer offset = size * (page - 1);
        List<Project> users =  adminDao.projectlist(offset,size);
        paginationDTO.setData(users);
        return paginationDTO;
    }
}


