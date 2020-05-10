package com.hzu.backstageservice.mapperss;


import com.hzu.backstageservice.model.Project;
import com.hzu.backstageservice.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface ProjectMapper {




    List<Project> getProjects(Integer offset, Integer size);

    Integer count();

}
