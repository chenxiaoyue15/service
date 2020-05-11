package com.hzu.community.api.controller.admin;

import com.hzu.community.api.dto.PaginationDTO;
import com.hzu.community.api.dto.ResultDTO;
import com.hzu.community.api.model.Company;
import com.hzu.community.api.model.Project;
import com.hzu.community.api.model.Question;
import com.hzu.community.api.model.User;
import com.hzu.community.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/HRuser_list")
    public String HRuserlist(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        PaginationDTO pagination = adminService.getHRusers(page, size);
        model.addAttribute("pagination", pagination);
        return "admin/HRuser_list";
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
    @GetMapping("/employment")
    public String employment(Model model,
                              @RequestParam(name = "page", defaultValue = "1") Integer page,
                              @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        PaginationDTO pagination = adminService.employment(page, size);
        model.addAttribute("pagination", pagination);
        return "admin/employment";
    }
    @ResponseBody
    @RequestMapping(value = "/deleted", method = RequestMethod.POST)
    public Object delete(@RequestBody Company company) {

        adminService.deleteById(company);

        return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/openit", method = RequestMethod.POST)
    public Object openit(@RequestBody User user) {

        adminService.openit(user);

        return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/closeit", method = RequestMethod.POST)
    public Object closeit(@RequestBody User user) {

        adminService.closeit(user);

        return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/added", method = RequestMethod.POST)
    public Object added(@RequestBody Project project) {

        adminService.added(project);

        return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/out", method = RequestMethod.POST)
    public Object out(@RequestBody Project project) {

        adminService.out(project);

        return ResultDTO.okOf();
    }

}