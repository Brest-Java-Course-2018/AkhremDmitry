package com.epam.brest.course.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * Department DAO Interface.
 */

public interface DepartmentDao {

    /**
     * Return all department from database.
     *
     * @return List departments
     * @throws DataAccessException on data access error.
     */
    List<Department> getAllDepartment();

    /**
     * Return department by ID from database.
     *
     * @param departmentId int
     * @return Department
     * @throws DataAccessException on data access error.
     */
    Department getDepartmentById(int departmentId);

    /**
     * Add department to database.
     *
     * @param department Department
     * @return Department
     * @throws DataAccessException on data access error.
     */
    Department addDepartment(Department department);

    /**
     * Update department in database.
     *
     * @param department Department
     * @throws DataAccessException on data access error.
     */
    void updateDepartment(Department department);

    /**
     * Remove department by ID from database.
     *
     * @param departmentId int
     * @throws DataAccessException on data access error.
     */
    void deleteDepartmentById(int departmentId);

}
