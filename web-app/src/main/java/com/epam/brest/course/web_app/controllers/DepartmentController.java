package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Department;
import com.epam.brest.course.dto.DepartmentDto;
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
     * Add department to db.
     *
     * @param department Department
     * @param result BindingResult
     * @return template name
     */
    @PostMapping(value = "/department")
    public final String addDepartment(@Valid Department department,
                                      BindingResult result,
                                      Model model) {

     if (result.hasErrors()){
         String navbarBrandText = "Add department";
         model.addAttribute("navbarBrandText", navbarBrandText);
         model.addAttribute("department", department);
         return "department";
     }else {
         departmentService.addDepartment(department);
         return "redirect:/departments";
     }
    }

    /**
     * Update department.
     *
     * @param department Department
     * @param result BindingResult
     * @return template name
     */
    @PostMapping(value = "/editDepartment/{id}")
    public final String updateDepartment(@Valid Department department, BindingResult result) {
        if (result.hasErrors()){
            return "redirect:/department";
        }else {
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
    public final String editDepartment(@PathVariable Integer id, Model model) {
        DepartmentDto department = departmentService.getDepartmentDtoById(id);
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
    public  final String deleteDepartment(@PathVariable Integer id){
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }
}