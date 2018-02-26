package com.epam.brest.cource.dao;

import com.epam.brest.cource.model.Department;

import java.util.List;

/**
 * Department DAO Interface
 */

public interface DepartmentDao {

    List<Department> getAllDepartment();
    Department getDepartmentById(int departmentId);
}
