package com.hzu.community.api.service;

import com.hzu.community.api.dao.GreatDao;
import com.hzu.community.api.model.Great;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreatService {
    @Autowired
    private GreatDao greatDao;
    public List<Great> findByAidAndUid(Integer aid, Integer uid) {
         List<Great> great = greatDao.findByAidAndUid(aid,uid);
         return great;
    }

    public void delete(Great great) {
        greatDao.delete(great);
    }

    public void saveAndFlush(Great great) {
        greatDao.saveAndFlush(great);
    }
}
