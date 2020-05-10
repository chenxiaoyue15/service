package com.hzu.community.api.controller.admin;

import com.hzu.community.api.dto.PaginationDTO;
import com.hzu.community.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/admin")
public class Question_listController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/question_list")
    public String question_list(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        PaginationDTO pagination = adminService.getQuestions(page, size);
        model.addAttribute("pagination", pagination);
        return "admin/question_list";
    }
    @GetMapping("/user_list")
    public String userlist(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        PaginationDTO pagination = adminService.getusers(page, size);
        model.addAttribute("pagination", pagination);
        return "admin/user_list";
    }
    @GetMapping("/company_list")
    public String companylist(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        PaginationDTO pagination = adminService.getcompanyusers(page, size);
        model.addAttribute("pagination", pagination);
        return "admin/company_list";
    }
    @GetMapping("/project_list")
    public String projectlist(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        PaginationDTO pagination = adminService.projectlist(page, size);
        model.addAttribute("pagination", pagination);
        return "admin/project_list";
    }
}