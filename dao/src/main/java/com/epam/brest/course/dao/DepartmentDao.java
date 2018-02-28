package com.epam.brest.course.dao;

import java.util.List;

/**
 * Department DAO Interface
 */

public interface DepartmentDao {

    List<Department> getAllDepartment();
    Department getDepartmentById(int departmentId);
    Department addDepartment(Department department);
    void updateDepartment(Department department);
    void deleteDepartmentById(int id);
}
