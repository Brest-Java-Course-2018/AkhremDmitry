package com.epam.brest.course.dao;

import java.util.List;

/**
 * Employee DAO Interface.
 */
public interface EmployeeDao {

    /**
     * Return all employee from database.
     *
     * @return List
     */
    List<Employee> getAllEmployee();

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
    void updateEmployee(Employee employee);

    /**
     * Remove employee by ID from database.
     *
     * @param employeeId int
     */
    void deleteEmployeeById(int employeeId);


}
