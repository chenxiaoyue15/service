package com.hzu.community.mapper;

import com.hzu.community.model.Great;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GreatMapper {

     List<Great> findByAidAndUid(@Param("aid")Integer aid,@Param("uid") Integer uid) ;

     void deleteLiked(Great great);

    void add(Great great);
}
