package com.epam.brest.course.dao;


import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;

import java.util.Collection;

/**
 * Department DAO Interface.
 */

public interface DepartmentDao {

    /**
     * Return all department from database.
     *
     * @return Collection departments
     */
    Collection<Department> getAllDepartment();

    /**
     * Return all department from database.
     *
     * @return Collection departments
     */
    Collection<DepartmentDto> getAllDepartmentDto();

    /**
     * Return all department with average salary from database.
     * @return Collection departmentsDto
     */
    Collection<DepartmentDtoWithAvgSalary> getAllDepartmentWithAvgSalary();

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
