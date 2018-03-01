package com.epam.brest.course.dao;

import java.util.List;

/**
 * Department DAO Interface.
 */

public interface DepartmentDao {

    /**
     * Return all department from database.
     * @return List
     */
    List<Department> getAllDepartment();

    /**
     * Return department by ID from database.
     * @param departmentId int
     * @return Department
     */
    Department getDepartmentById(int departmentId);

    /**
     * Add department to database.
     * @param department Department
     * @return Department
     */
    Department addDepartment(Department department);

    /**
     * Return department by name from database.
     * @param  departmentName String
     * @return Department
     */
    Department getDepartmentByName(String departmentName);

    /**
     * Update department in database.
     * @param department Department
     */
    void updateDepartment(Department department);

    /**
     * Remove department by ID from database.
     * @param departmentId int
     */
    void deleteDepartmentById(int departmentId);

}
