package com.epam.brest.course.service;

import com.epam.brest.course.dao.Department;
import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;
import org.springframework.dao.DataAccessException;

import java.util.Collection;


/**
 * Department service Interface.
 */


public interface DepartmentService {

//    /**
//     * Return department by ID from database.
//     *
//     * @param departmentId int
//     * @return Department
//     * @throws DataAccessException on data access error.
//     */
//    Department getDepartmentById(int departmentId);

    /**
     * Return departmentDto by ID from database.
     *
     * @param departmentId int
     * @return DepartmentDto
     * @throws DataAccessException on data access error.
     */
    DepartmentDto getDepartmentDtoById(int departmentId);

    /**
     * Return all department from database.
     *
     * @return List
     */
    Collection<DepartmentDto> getAllDepartment();

    /**
     * Return all department with average salary from database.
     * @return list departmentsDto
     * @throws DataAccessException on data access error.
     */
    Collection<DepartmentDtoWithAvgSalary> getAllDepartmentWithAvgSalary();


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
