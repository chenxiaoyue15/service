package com.hzu.community.api.controller;

import com.hzu.community.api.dto.PaginationDTO;
import com.hzu.community.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class adminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/login")
    public String index( ){

        return "admin/login";
    }
    @RequestMapping("/admin/home")
    public String home(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size
    ){
        PaginationDTO pagination = adminService.getusers(page,size);
        model.addAttribute("pagination", pagination);
        return "admin/home";
    }




}
