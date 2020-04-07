package com.hzu.community.mapper;

import com.hzu.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserMapper {
    User selectById(Long id);

    List<User> select(User user);

    void updateToken(String name, String token);


}
