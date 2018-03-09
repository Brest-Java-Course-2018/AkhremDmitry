package com.epam.brest.course.service;

import com.epam.brest.course.dao.Department;
import com.epam.brest.course.dao.DepartmentDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LogManager.getLogger();


    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public void updateDepartmentDescription(Integer departmentId, String description) {
        LOGGER.debug("getDepartmentById({}, {})", departmentId, description);
        Department department = departmentDao.getDepartmentById(departmentId);
        department.setDescription(description);
        departmentDao.updateDepartment(department);
    }

    @Override
    public List<Department> getAllDepartment() {
        LOGGER.debug("getAllDepartment");
        List<Department> departments;
        departments = departmentDao.getAllDepartment();
        return departments;
    }

    @Override
    public Department addDepartment(final Department department) {
        LOGGER.debug("addDepartment({})", department);
        if (department.getDepartmentName().matches(".*[\\W].*")){
            throw new IllegalArgumentException("Department name can contain only letters, numbers and underline");
        }
        return departmentDao.addDepartment(department);
    }

    @Override
    public void deleteDepartmentById(int departmentId) {
        LOGGER.debug("deleteDepartmentById({})", departmentId);
        departmentDao.deleteDepartmentById(departmentId);
    }
}
