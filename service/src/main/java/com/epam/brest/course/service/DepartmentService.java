package com.epam.brest.course.service;

import com.epam.brest.course.dao.Department;
import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;

import java.util.Collection;


/**
 * Department service Interface.
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
     * Return all department from database.
     *
     * @return List
     */
    Collection<DepartmentDto> getAllDepartmentDto();

    /**
     * Return all department with average salary from database.
     * @return list departmentsDto
     */
    Collection<DepartmentDtoWithAvgSalary> getAllDepartmentWithAvgSalary();


    /**
     * Add department to database.
     *
     * @param department Department
     * @return Department
     */
    Department addDepartment(Department department);

    /**
     * Update department in database.
     *
     * @param department Department
     */
    void updateDepartment(Department department);

    /**
     * Remove department by ID from database.
     *
     * @param departmentId int
     */
    void deleteDepartmentById(int departmentId);


}
