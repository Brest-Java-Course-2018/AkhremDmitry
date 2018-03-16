package com.epam.brest.course.service;

import com.epam.brest.course.dao.Employee;
import org.springframework.dao.DataAccessException;

import java.util.Collection;


/**
 * Employee service Interface.
 */
public interface EmployeeService {

    /**
     * Return all employee from database.
     *
     * @return List
     */
    Collection<Employee> getAllEmployee();

    /**
     * Return all employee with salary in the range from database.
     *
     * @param minSalary int
     * @param maxSalary int
     * @return List
     * @throws DataAccessException on data access error.
     */
    Collection<Employee> getAllEmployeeWhere(int minSalary, int maxSalary);

    /**
     * Return employee by ID from database.
     *
     * @param employeeId int
     * @return Employee
     * @throws DataAccessException on data access error.
     */
    Employee getEmployeeById(int employeeId);

    /**
     * Add employee to database.
     *
     * @param employee Employee
     * @return Employee
     * @throws DataAccessException on data access error.
     */
    Employee addEmployee(Employee employee);

    /**
     * Update salary in database.
     *
     * @param employeeId Employee
     * @param salary     int
     * @throws DataAccessException on data access error.
     */
    void updateEmployeeSalary(int employeeId, int salary);

    /**
     * Remove employee by ID from database.
     *
     * @param employeeId int
     * @throws DataAccessException on data access error.
     */
    void deleteEmployeeById(int employeeId);
}
