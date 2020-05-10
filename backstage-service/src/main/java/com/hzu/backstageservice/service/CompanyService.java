package com.hzu.backstageservice.service;


import com.hzu.backstageservice.mappers.CompanyMapper;
import com.hzu.backstageservice.mappers.UserMapper;
import com.hzu.backstageservice.model.Company;
import com.hzu.backstageservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;


    public List<Company> companylist(Integer offset, Integer size) {

        return companyMapper.selectcompanyUsers(offset,size);
    }

    public Integer count() {

        return companyMapper.count();
    }
}
