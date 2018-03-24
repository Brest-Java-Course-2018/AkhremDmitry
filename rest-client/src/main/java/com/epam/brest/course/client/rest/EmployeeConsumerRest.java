package com.epam.brest.course.client.rest;

import com.epam.brest.course.dao.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

public class EmployeeConsumerRest implements EmployeeService {

    private String url;

    private RestTemplate restTemplate;

    public EmployeeConsumerRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Employee> getAllEmployee() {
        ResponseEntity responseEntity = restTemplate
                .getForEntity(url, List.class);
        Collection<Employee> employees = (Collection<Employee>) responseEntity.getBody();
        return employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        ResponseEntity<Employee> responseEntity = restTemplate
                .getForEntity(url + "/" + employeeId, Employee.class);
        Employee result = responseEntity.getBody();
        return result;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        ResponseEntity<Employee> responseEntity = restTemplate
                .postForEntity(url, employee, Employee.class);
        Employee result = responseEntity.getBody();
        return result;
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        restTemplate.delete(url + "/" + employeeId);
    }

    @Override
    public void updateEmployee(Employee employee) {
        restTemplate.put(url, employee);
    }

    @Override
    public Collection<Employee> getAllEmployeeWhere(int minSalary, int maxSalary) {
        return null;
    }

    @Override
    public void updateEmployeeSalary(int employeeId, int salary) {

    }
}
