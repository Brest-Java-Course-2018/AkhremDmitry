package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Employee controller.
 */
@Controller
public class EmployeeController {

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
    public final String employees() {
        return "employees";
    }

    /**
     * Show editEmployee page.
     *
     * @return template name
     */
    @GetMapping(value = "/editEmployee")
    public final String editEmployee() {
        return "editEmployee";
    }
}