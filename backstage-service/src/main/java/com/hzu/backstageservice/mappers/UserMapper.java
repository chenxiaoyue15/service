package com.hzu.backstageservice.mappers;


import com.hzu.backstageservice.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface UserMapper {


    List<User> selectUsers(Integer offset, Integer size);

    Integer count();

    List<User> selectHRUsers(Integer offset, Integer size);

    Integer HRcount();

    void openit(User user);

    void closeit(User user);
}
