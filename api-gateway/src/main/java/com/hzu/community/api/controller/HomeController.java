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
public class HomeController {

    @Autowired
    private HotTagCache hotTagCache;
    @RequestMapping("/home")
    public String index(Model model,
                        @RequestParam(name = "tag",required = false) String tag
                        ){
        List<String> tags = hotTagCache.getHots();
        model.addAttribute("tags", tags);
        model.addAttribute("tag", tag);
        return "home";
    }





}
