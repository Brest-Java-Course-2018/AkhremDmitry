package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    /**
     * Show department page.
     *
     * @return template name
     */
    @GetMapping(value = "/department")
    public final String department() {
        return "department";
    }

    /**
     * Show departments page.
     *
     * @return template name
     */
    @GetMapping(value = "/departments")
    public final String departments() {
        return "departments";
    }

    /**
     * Show editDepartment page.
     *
     * @return template name
     */
    @GetMapping(value = "/editDepartment")
    public final String editDepartment() {
        return "editDepartment";
    }
}