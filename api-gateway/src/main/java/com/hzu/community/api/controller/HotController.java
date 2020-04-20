package com.hzu.community.api.controller;

import com.hzu.community.api.dto.PaginationDTO;
import com.hzu.community.api.model.HotTagCache;
import com.hzu.community.api.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class HotController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private HotTagCache hotTagCache;
    @RequestMapping("/hot")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        @RequestParam(name = "search",required = false) String search,
                        @RequestParam(name = "tag",required = false) String tag,
                        @RequestParam(name = "sort", required = false) String sort
                        ){
        PaginationDTO pagination = questionService.getQuestions(search,tag,page,size,sort);
        List<String> tags = hotTagCache.getHots();
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        model.addAttribute("tags", tags);
        model.addAttribute("tag", tag);
        model.addAttribute("sort", sort);
        return "hot";
    }





}
