package com.epam.brest.course.dao;

import org.springframework.dao.DataAccessException;

import java.util.Collection;


/**
 * Employee DAO Interface.
 */
public interface EmployeeDao {

    /**
     * Return all employee from database.
     *
     * @return List employees
     * @throws DataAccessException on data access error.
     */
    Collection<Employee> getAllEmployee();

    /**
     * Return all employee with salary in the range from database.
     *
     * @param whereSql  String
     * @param minSalary int
     * @param maxSalary int
     * @return List
     * @throws DataAccessException on data access error.
     */
    Collection<Employee> getAllEmployeeWhere(final String whereSql,
                                       final int minSalary,
                                       final int maxSalary);

    /**
     * Return List employees by departmentId from database.
     *
     * @param departmentId int
     * @return List<Employee>
     * @throws DataAccessException on data access error.
     */
    Collection<Employee> getAllEmployeeByDepartmentId(final int departmentId);

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
     * Update employee in database.
     *
     * @param employee Employee
     * @throws DataAccessException on data access error.
     */
    void updateEmployee(Employee employee);

    /**
     * Remove employee by ID from database.
     *
     * @param employeeId int
     * @throws DataAccessException on data access error.
     */
    void deleteEmployeeById(int employeeId);

    /**
     * Return number of employees of the department with ID.
     *
     * @param departmentID department Id
     * @return int number of employees.
     */
    int getNumberEmployeesInDepartment(int departmentID);


}
