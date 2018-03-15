package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

/**
 * Employee controller.
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    /**
     * Show employee page.
     *
     * @return template name
     */
    @GetMapping(value = "/employee")
    public final String employee() {
        return "employee";
    }

    /**
     * Show employees page.
     *
     * @return template name
     */
    @GetMapping(value = "/employees")
    public final String employees(Model model) {
        Collection<Employee> employees = employeeService.getAllEmployee();
        model.addAttribute("employees", employees);
        return "employees";
    }

    /**
     * Show editEmployee page.
     *
     * @return template name
     */
    @GetMapping(value = "/editEmployee/{id}")
    public final String editEmployee(@PathVariable Integer id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "editEmployee";
    }
}