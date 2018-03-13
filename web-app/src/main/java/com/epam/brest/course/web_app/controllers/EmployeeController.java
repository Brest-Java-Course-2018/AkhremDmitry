package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @GetMapping(value = "/employee")
    public String employee(Model model) {
        return "employee";
    }

    @GetMapping(value = "/employees")
    public String employees(Model model) {
        return "employees";
    }

    @GetMapping(value = "/editEmployee")
    public String editEmployee(Model model) {
        return "editEmployee";
    }
}