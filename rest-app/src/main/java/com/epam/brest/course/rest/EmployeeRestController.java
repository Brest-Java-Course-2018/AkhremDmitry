package com.epam.brest.course.rest;

import com.epam.brest.course.dao.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/employees")
    @ResponseStatus(HttpStatus.CREATED)
    Employee addEmployee(@RequestBody Employee employee){
        LOGGER.debug("addEmployee({})", employee);
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping(value = "/employees/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    void deleteEmployeeById(@PathVariable(value = "id") Integer employeeId){
        LOGGER.debug("deleteEmployeeById({})", employeeId);
        employeeService.deleteEmployeeById(employeeId);
    }

    @PutMapping(value = "/employees")
    @ResponseStatus(HttpStatus.FOUND)
    void updateEmployee(@RequestBody Employee employee){
        LOGGER.debug("updateEmployee({})", employee);
        employeeService.updateEmployee(employee);
    }
}
