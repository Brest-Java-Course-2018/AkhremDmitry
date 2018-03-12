package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditEmployeeController {

    @GetMapping(value = "/editEmployee")
    public String hello(Model model) {
//        model.addAttribute("name", name);
        return "editEmployee";
    }
}