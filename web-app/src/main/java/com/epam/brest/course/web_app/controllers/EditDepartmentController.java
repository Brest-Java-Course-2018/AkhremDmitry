package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditDepartmentController {

    @GetMapping(value = "/editDepartment")
    public String hello(Model model) {
//        model.addAttribute("name", name);
        return "editDepartment";
    }
}