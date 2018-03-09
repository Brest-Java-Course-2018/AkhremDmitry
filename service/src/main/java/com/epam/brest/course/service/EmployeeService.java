package com.epam.brest.course.service;

import com.epam.brest.course.dao.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    List<Employee> getAllEmployeeWhere(int minSalary, int maxSalary);

    /**
     * Return employee by ID from database.
     *
     * @param employeeId int
     * @return Employee
     */
    Employee getEmployeeById(int employeeId);

    /**
     * Add employee to database.
     *
     * @param employee Employee
     * @return Employee
     */
    Employee addEmployee(Employee employee);

    /**
     * Update employee in database.
     *
     * @param employee Employee
     */
    void updateEmployeeSalary(int employeeId, int salary);

    /**
     * Remove employee by ID from database.
     *
     * @param employeeId int
     */
    void deleteEmployeeById(int employeeId);
}
