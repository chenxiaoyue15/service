package com.hzu.backstageservice.mapperss;


import com.hzu.backstageservice.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface ProjectMapper {


    List<User> selectUsers(Integer offset, Integer size);
}