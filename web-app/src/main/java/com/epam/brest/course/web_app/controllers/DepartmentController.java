package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Department;
import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    /**
     * DepartmentService.
     */
    @Autowired
    private DepartmentService departmentService;


    /**
     * Show departments page.
     *
     * @param model Model
     * @return template name
     */
    @GetMapping(value = "/departments")
    public final String departments(final Model model) {
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
    public final String department(final Model model) {
        String navbarBrandText = "Add department";
        model.addAttribute("navbarBrandText", navbarBrandText);
        model.addAttribute("department", new Department());
        return "department";
    }

    /**
     * Add department to db.
     *
     * @param department Department
     * @param result     BindingResult
     * @param model      Model
     * @return template name
     */
    @PostMapping(value = "/department")
    public final String addDepartment(@Valid final Department department,
                                      final BindingResult result,
                                      final Model model) {

        if (result.hasErrors()) {
            String navbarBrandText = "Add department";
            model.addAttribute("navbarBrandText", navbarBrandText);
            return "department";
        } else {
            departmentService.addDepartment(department);
            return "redirect:/departments";
        }
    }

    /**
     * Update department.
     *
     * @param department Department
     * @param result     BindingResult
     * @param model      Model
     * @return template name
     */
    @PostMapping(value = "/editDepartment/{id}")
    public final String updateDepartment(@Valid final Department department,
                                         final BindingResult result,
                                         final Model model) {
        if (result.hasErrors()) {
            model.addAttribute("navbarBrandText", "Edit department");
            return "department";
        } else {
            departmentService.updateDepartment(department);
            return "redirect:/departments";
        }
    }

    /**
     * Show editDepartment page.
     *
     * @param id    department id
     * @param model Model
     * @return template name
     */
    @GetMapping(value = "/editDepartment/{id}")
    public final String editDepartment(@PathVariable final Integer id,
                                       final Model model) {
        Department department = departmentService.getDepartmentById(id);
        String navbarBrandText = "Edit department";
        model.addAttribute("navbarBrandText", navbarBrandText);
        model.addAttribute("department", department);

        return "department";
    }

    /**
     * Delete department.
     *
     * @param id departmentId
     * @return template name
     */
    @GetMapping(value = "/department/{id}/delete")
    public final String deleteDepartment(@PathVariable final Integer id) {
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }
}
