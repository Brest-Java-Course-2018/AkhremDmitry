package com.epam.brest.course.service;

import com.epam.brest.course.dao.Department;
import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.dao.EmployeeDao;
import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;


/**
 * Department service.
 */
public class DepartmentServiceImpl implements DepartmentService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * DepartmentDao.
     */
    private DepartmentDao departmentDao;

    /**
     * EmployeeDap
     */
    private EmployeeDao employeeDao;

    /**
     * Constructor DepartmentServiceImpl.
     *
     * @param departmentDao DepartmentDao
     */
    public DepartmentServiceImpl(final DepartmentDao departmentDao,
                                 final EmployeeDao employeeDao) {
        this.departmentDao = departmentDao;
        this.employeeDao = employeeDao;
    }

    @Override
    public final Department getDepartmentById(final int departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public final Collection<DepartmentDto> getAllDepartmentDto() {
        LOGGER.debug("getAllDepartment");
        Collection<DepartmentDto> departments;
        departments = departmentDao.getAllDepartmentDto();
        return departments;
    }

    @Override
    public Collection<DepartmentDtoWithAvgSalary> getAllDepartmentWithAvgSalary() {
        Collection<DepartmentDtoWithAvgSalary> departments =
                departmentDao.getAllDepartmentWithAvgSalary();
        return departments;
    }

    @Override
    public final Department addDepartment(final Department department) {
        LOGGER.debug("addDepartment({})", department);
//        if (department.getDepartmentName().matches(".*[^a-zA-Zа-яА-Я0-9_].*")) {
//            throw new IllegalArgumentException("Department "
//                    + "name can contain only letters, numbers and underline");
//        }
        return departmentDao.addDepartment(department);
    }

    @Override
    public void updateDepartment(Department department) {
        LOGGER.debug("updateDepartment({})", department);
        departmentDao.updateDepartment(department);
    }

    @Override
    public final void deleteDepartmentById(final int departmentId) {
        LOGGER.debug("deleteDepartmentById({})", departmentId);
        if(employeeDao.getNumberEmployeesInDepartment(departmentId) != 0){
            throw new IllegalArgumentException("You can't delete the Department because it has the employees");
        }
        departmentDao.deleteDepartmentById(departmentId);
    }
}
