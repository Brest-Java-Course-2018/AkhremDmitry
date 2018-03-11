package com.epam.brest.course.service;

import com.epam.brest.course.dao.Employee;

import java.util.List;

/**
 * Employee service Interface.
 */
public interface EmployeeService {

    /**
     * Return all employee from database.
     *
     * @return List
     */
    List<Employee> getAllEmployee();

    /**
     * Return all employee with salary in the range from database.
     *
     * @param minSalary int
     * @param maxSalary int
     * @return List
     */
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
     * Update salary in database.
     *
     * @param employeeId Employee
     * @param salary int
     */
    void updateEmployeeSalary(int employeeId, int salary);

    /**
     * Remove employee by ID from database.
     *
     * @param employeeId int
     */
    void deleteEmployeeById(int employeeId);
}
