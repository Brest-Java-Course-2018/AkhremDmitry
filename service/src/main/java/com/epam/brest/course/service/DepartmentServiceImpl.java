package com.epam.brest.course.service;

import com.epam.brest.course.dao.Department;
import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
     * Constructor DepartmentServiceImpl.
     *
     * @param departmentDao DepartmentDao
     */
    public DepartmentServiceImpl(final DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public final Department getDepartmentById(final int departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public final DepartmentDto getDepartmentDtoById(final int departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        Department department = departmentDao.getDepartmentById(departmentId);
        DepartmentDto departmentDto = new DepartmentDto(department.getDepartmentId(),
                department.getDepartmentName(),
                department.getDescription());
        return departmentDto;
    }

    @Override
    public final void updateDepartmentDescription(final Integer departmentId,
                                                  final String description) {
        LOGGER.debug("getDepartmentById({}, {})", departmentId, description);
        Department department = departmentDao.getDepartmentById(departmentId);
        department.setDescription(description);
        departmentDao.updateDepartment(department);
    }

    @Override
    public final Collection<DepartmentDto> getAllDepartment() {
        LOGGER.debug("getAllDepartment");
        Collection<Department> departments;
        departments = departmentDao.getAllDepartment();
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        for (Department department: departments){
            departmentDtos.add(new DepartmentDto(department.getDepartmentId(),
                    department.getDepartmentName(),
                    department.getDescription()));
        }
        return departmentDtos;
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
        if (department.getDepartmentName().matches(".*[^a-zA-Zа-яА-Я0-9_].*")) {
            throw new IllegalArgumentException("Department "
                    + "name can contain only letters, numbers and underline");
        }
        return departmentDao.addDepartment(department);
    }

    @Override
    public final void deleteDepartmentById(final int departmentId) {
        LOGGER.debug("deleteDepartmentById({})", departmentId);
        departmentDao.deleteDepartmentById(departmentId);
    }
}
