package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Employee;
import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.service.DepartmentService;
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

    @Autowired
    DepartmentService departmentService;
    /**
     * Show employee page.
     *
     * @return template name
     */
    @GetMapping(value = "/employee")
    public final String employee(Model model) {
        String navbarBrandText = "Add employee";
        Collection<DepartmentDto> departments = departmentService.getAllDepartment();
        model.addAttribute("navbarBrandText", navbarBrandText);
        model.addAttribute("departments", departments);
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
        Collection<DepartmentDto> departments = departmentService.getAllDepartment();

        DepartmentDto departmentDto= null;
        for(DepartmentDto curDep: departments){
            if (curDep.getDepartmentId().equals(id)){
                departmentDto=curDep;
            }
        }
        departments.remove(departmentDto);

        String navbarBrandText = "Edit employee";
        model.addAttribute("navbarBrandText", navbarBrandText);
        model.addAttribute("employee", employee);
        model.addAttribute("employeeDepartment", departmentDto);
        model.addAttribute("departments", departments);
        return "employee";
    }
}