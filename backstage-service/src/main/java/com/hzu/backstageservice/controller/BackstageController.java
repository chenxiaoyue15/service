package com.hzu.backstageservice.controller;

import com.hzu.backstageservice.common.RestResponse;
import com.hzu.backstageservice.model.Company;
import com.hzu.backstageservice.model.Project;
import com.hzu.backstageservice.model.Question;
import com.hzu.backstageservice.model.User;
import com.hzu.backstageservice.service.CompanyService;
import com.hzu.backstageservice.service.ProjectService;
import com.hzu.backstageservice.service.QuestionService;
import com.hzu.backstageservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("backstage")
@RestController
public class BackstageController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ProjectService projectService;
    @RequestMapping("questionlist")
    public RestResponse<List<Question>> questionlist(Integer offset, Integer size){
        List<Question> list = null;
        list = questionService.getQuestions(offset,size);
        return RestResponse.success(list);
    }
    @RequestMapping("userlist")
    public RestResponse<List<User>> userlist(Integer offset, Integer size){
        List<User> list = null;
        list = userService.getUsers(offset,size);
        return RestResponse.success(list);
    }
    @RequestMapping("companylist")
    public RestResponse<List<Company>> companylist(Integer offset, Integer size){
        List<Company> list = null;
        list = companyService.companylist(offset,size);
        return RestResponse.success(list);
    }
    @RequestMapping("projectlist")
    public RestResponse<List<Project>> projectlist(Integer offset, Integer size){
        List<Project> list = null;
        list = projectService.getProjects(offset,size);
        return RestResponse.success(list);
    }

    @RequestMapping(value="questioncount")
    public RestResponse<Integer> questioncount(){

        Integer count =  questionService.count();

        return RestResponse.success(count);
    }
    @RequestMapping(value="companycount")
    public RestResponse<Integer> companycount(){

        Integer count =  companyService.count();

        return RestResponse.success(count);
    }
    @RequestMapping(value="usercount")
    public RestResponse<Integer> usercount(){

        Integer count =  userService.count();

        return RestResponse.success(count);
    }
    @RequestMapping(value="projectcount")
    public RestResponse<Integer> projectcount(){

        Integer count =  projectService.count();

        return RestResponse.success(count);
    }
    @RequestMapping(value="deletequestion")
    public RestResponse<Object> deletequestion(@RequestBody Question question){

        questionService.deleteById(question);

        return RestResponse.success();
    }
}
