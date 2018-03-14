package com.epam.brest.course.service;

import com.epam.brest.course.dao.Department;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Department service Interface.
 */

@Service
public interface DepartmentService {

    /**
     * Return department by ID from database.
     *
     * @param departmentId int
     * @return Department
     * @throws DataAccessException on data access error.
     */
    Department getDepartmentById(int departmentId);

    /**
     * Update department description in database.
     *
     * @param departmentId Integer
     * @param description  String
     * @throws DataAccessException on data access error.
     */
    void updateDepartmentDescription(Integer departmentId, String description);

    /**
     * Return all department from database.
     *
     * @return List
     */
    List<Department> getAllDepartment();


    /**
     * Add department to database.
     *
     * @param department Department
     * @return Department
     * @throws DataAccessException on data access error.
     */
    Department addDepartment(Department department);

    /**
     * Remove department by ID from database.
     *
     * @param departmentId int
     * @throws DataAccessException on data access error.
     */
    void deleteDepartmentById(int departmentId);


}
