package com.hzu.backstageservice.mapperss;


import com.hzu.backstageservice.model.Project;
import com.hzu.backstageservice.model.User;
import com.hzu.backstageservice.model.UserMsg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface ProjectMapper {




    List<Project> getProjects(Integer offset, Integer size);

    Integer count();

    List<UserMsg> getUserMsg(Integer offset, Integer size);

    Integer employmentcount();

    List<Project> getProject(Long projectId);

    void added(Project project);

    void out(Project project);
}
