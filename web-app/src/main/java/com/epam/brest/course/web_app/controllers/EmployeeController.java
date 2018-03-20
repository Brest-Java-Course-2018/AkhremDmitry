package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Employee;
import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.service.DepartmentService;
import com.epam.brest.course.service.EmployeeService;
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
 * Employee controller.
 */
@Controller
public class EmployeeController {

    /**
     * EmployeeService.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * DepartmentService.
     */
    @Autowired
    private DepartmentService departmentService;

    /**
     * Show employee page.
     *
     * @param model Model
     * @return template name
     */
    @GetMapping(value = "/employee")
    public final String employee(final Model model) {
        String navbarBrandText = "Add employee";
        Collection<DepartmentDto> departments = departmentService
                .getAllDepartmentDto();
        model.addAttribute("navbarBrandText", navbarBrandText);
        model.addAttribute("departments", departments);
        model.addAttribute("employeeDepartment", new DepartmentDto());
        model.addAttribute("employee", new Employee());
        return "employee";
    }

    /**
     * Add employee to db.
     *
     * @param employee Employee
     * @param result   BindingResult
     * @param model    Model
     * @return template name
     */
    @PostMapping(value = "/employee")
    public final String addEmployee(@Valid final Employee employee,
                                    final BindingResult result,
                                    final Model model) {
        if (result.hasErrors()) {
            String navbarBrandText = "Add employee";
            Collection<DepartmentDto> departments = departmentService
                    .getAllDepartmentDto();
            model.addAttribute("navbarBrandText", navbarBrandText);
            model.addAttribute("departments", departments);
            model.addAttribute("employeeDepartment", new DepartmentDto());
            return "employee";
        } else {
            employeeService.addEmployee(employee);
            return "redirect:/employees";
        }

    }

    /**
     * Update employee.
     *
     * @param employee Employee
     * @param result   BindingResult
     * @param model    Model
     * @param id       Integer
     * @return template name
     */
    @PostMapping(value = "/editEmployee/{id}")
    public final String updateEmployee(@PathVariable final Integer id,
                                       @Valid final Employee employee,
                                       final BindingResult result,
                                       final Model model) {
        if (result.hasErrors()) {
            Collection<DepartmentDto> departments = departmentService
                    .getAllDepartmentDto();

            DepartmentDto departmentDto = null;
            for (DepartmentDto curDep : departments) {
                if (curDep.getDepartmentId()
                        .equals(employee.getDepartmentId())) {
                    departmentDto = curDep;
                }
            }
            departments.remove(departmentDto);

            model.addAttribute("navbarBrandText", "Edit employee");
            model.addAttribute("departments", departments);
            model.addAttribute("employeeDepartment", departmentDto);
            return "employee";
        } else {
            employeeService.updateEmployee(employee);
            return "redirect:/employees";
        }

    }

    /**
     * Show employees page.
     *
     * @param model Model
     * @return template name
     */
    @GetMapping(value = "/employees")
    public final String employees(final Model model) {
        Collection<Employee> employees = employeeService.getAllEmployee();
        model.addAttribute("employees", employees);
        return "employees";
    }

    /**
     * Show editEmployee page.
     *
     * @param id    Integer
     * @param model Model
     * @return template name
     */
    @GetMapping(value = "/editEmployee/{id}")
    public final String editEmployee(@PathVariable final Integer id,
                                     final Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        Collection<DepartmentDto> departments = departmentService
                .getAllDepartmentDto();

        DepartmentDto departmentDto = null;
        for (DepartmentDto curDep : departments) {
            if (curDep.getDepartmentId().equals(employee.getDepartmentId())) {
                departmentDto = curDep;
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

    /**
     * Delete employee from db.
     *
     * @param id Integer
     * @return template name
     */
    @GetMapping(value = "/employee/{id}/delete")
    public final String deleteEmployee(@PathVariable final Integer id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
