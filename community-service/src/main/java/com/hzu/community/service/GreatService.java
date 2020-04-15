package com.hzu.community.service;

import com.hzu.community.mapper.GreatMapper;
import com.hzu.community.model.Great;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreatService {
    @Autowired
    private GreatMapper greatMapper;
    public List<Great> findByAidAndUid(Integer aid, Integer uid) {
        return greatMapper.findByAidAndUid(aid,uid);
    }

    public void deleteLiked(Great great) {
        greatMapper.deleteLiked(great);
    }

    public void addLiked(Great great) {
        greatMapper.add(great);
    }


}
