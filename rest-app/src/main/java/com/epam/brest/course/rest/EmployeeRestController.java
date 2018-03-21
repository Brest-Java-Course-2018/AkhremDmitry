package com.epam.brest.course.rest;

import com.epam.brest.course.dao.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class EmployeeRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employees")
    Collection<Employee> getAllEmployees(){
        LOGGER.debug("getAllEmployees()");
        return employeeService.getAllEmployee();
    }

    @GetMapping(value = "/employees/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    Employee getEmployeeById(@PathVariable(value = "id") Integer id){
        LOGGER.debug("getEmployeeById({})", id);
        return employeeService.getEmployeeById(id);
    }
}
