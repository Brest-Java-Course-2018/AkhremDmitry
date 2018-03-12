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
     * Return all employee with salary in the range from database.
     *
     * @param whereSql  String
     * @param minSalary int
     * @param maxSalary int
     * @return List
     */
    List<Employee> getAllEmployeeWhere(final String whereSql,
                                       final int minSalary,
                                       final int maxSalary);

    /**
     * Return List employees by departmentId from database.
     *
     * @param departmentId int
     * @return List<Employee>
     */
    List<Employee> getAllEmployeeByDepartmentId(final int departmentId);

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
