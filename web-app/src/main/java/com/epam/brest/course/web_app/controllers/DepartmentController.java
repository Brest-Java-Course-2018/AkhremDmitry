package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {


    @Autowired
    DepartmentService departmentService;


    /**
     * Show departments page.
     *
     * @param model Model
     * @return template name
     */
    @GetMapping(value = "/departments")
    public final String departments(Model model) {
        Collection<DepartmentDtoWithAvgSalary> departments =
                departmentService.getAllDepartmentWithAvgSalary();
        model.addAttribute("departments", departments);
        return "departments";
    }

    /**
     * Show department page.
     *
     * @param model Model
     * @return template name
     */
    @GetMapping(value = "/department")
    public final String department(Model model) {
        String navbarBrandText = "Add department";
        model.addAttribute("navbarBrandText", navbarBrandText);
        model.addAttribute("department", new DepartmentDto());
        return "department";
    }

    /**
     * Show editDepartment page.
     *
     * @param id    department id
     * @param model Model
     * @return template name
     */
    @GetMapping(value = "/editDepartment/{id}")
    public final String editDepartment(@PathVariable Integer id, Model model) {
        DepartmentDto department = departmentService.getDepartmentDtoById(id);
        String navbarBrandText = "Edit department";
        model.addAttribute("navbarBrandText", navbarBrandText);
        model.addAttribute("department", department);

        return "department";
    }

//    @PostMapping(value = "/editDepartment/{id}")
//    public final String saveDepartment(@PathVariable Integer id, Model model) {
//
//        return "redirect:department";
//    }
}