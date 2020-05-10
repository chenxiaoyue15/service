package com.hzu.backstageservice.service;



import com.hzu.backstageservice.mapper.QuestionMapper;
import com.hzu.backstageservice.mapperss.ProjectMapper;
import com.hzu.backstageservice.model.Project;
import com.hzu.backstageservice.model.Question;
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
}
