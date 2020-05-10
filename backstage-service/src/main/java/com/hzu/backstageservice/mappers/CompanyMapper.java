package com.hzu.backstageservice.mappers;


import com.hzu.backstageservice.model.Company;
import com.hzu.backstageservice.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface CompanyMapper {


    List<Company> selectcompanyUsers(Integer offset, Integer size);

    Integer count();

}
