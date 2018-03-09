package com.epam.brest.course.service;

import com.epam.brest.course.dao.Employee;
import com.epam.brest.course.dao.EmployeeDao;

import java.util.List;

public class EmployeeServiceImp implements EmployeeService{

    private EmployeeDao employeeDao;

    public void setEmployeeDao(final EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }

    @Override
    public List<Employee> getAllEmployeeWhere(final int minSalary,
                                              final int maxSalary) {
        String whereSql = " WHERE salary >= :minSalary AND salary <= :maxSalary";
        List<Employee> employees = employeeDao
                .getAllEmployeeWhere(whereSql, minSalary, maxSalary);
        return employees;
    }

    @Override
    public Employee getEmployeeById(final int employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public Employee addEmployee(final Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public void updateEmployeeSalary(final int employeeId, final int salary) {
        Employee employee = employeeDao.getEmployeeById(employeeId);
        employee.setSalary(salary);
        employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployeeById(final int employeeId) {
        employeeDao.deleteEmployeeById(employeeId);
    }
}
