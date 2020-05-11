package com.hzu.backstageservice.service;



import com.hzu.backstageservice.mapper.QuestionMapper;
import com.hzu.backstageservice.mapperss.ProjectMapper;
import com.hzu.backstageservice.model.Project;
import com.hzu.backstageservice.model.Question;
import com.hzu.backstageservice.model.UserMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectService {
@Autowired
private ProjectMapper projectMapper;



    public Integer count() {

        return projectMapper.count();
    }




    public List<Project> getProjects(Integer offset, Integer size) {
        return projectMapper.getProjects(offset,size);
    }

    public List<UserMsg> getUserMsg(Integer offset, Integer size) {
        return projectMapper.getUserMsg(offset,size);
    }

    public Integer employmentcount() {

        return projectMapper.employmentcount();
    }

    public List<Project> get1Project(Long projectId) {
        return projectMapper.getProject(projectId);
    }

    public void added(Project project) {
        projectMapper.added(project);
    }

    public void out(Project project) {
        projectMapper.out(project);

    }
}
