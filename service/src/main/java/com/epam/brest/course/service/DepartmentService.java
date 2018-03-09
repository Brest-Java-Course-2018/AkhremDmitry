package com.epam.brest.course.service;

import com.epam.brest.course.dao.Department;

import java.util.List;


/**
 * Department DAO Interface.
 */

public interface DepartmentService {

    /**
     * Return department by ID from database.
     *
     * @param departmentId int
     * @return Department
     */
    Department getDepartmentById(int departmentId);

    /**
     * Add department to database.
     *
     * @param departmentId Integer
     * @return Department
     */
    void updateDepartmentDescription(Integer departmentId, String description);

    /**
     * Return all department from database.
     * @return List
     */
    List<Department> getAllDepartment();


    /**
     * Add department to database.
     * @param department Department
     * @return Department
     */
    Department addDepartment(Department department);

    /**
     * Remove department by ID from database.
     * @param departmentId int
     */
    void deleteDepartmentById(int departmentId);


}
