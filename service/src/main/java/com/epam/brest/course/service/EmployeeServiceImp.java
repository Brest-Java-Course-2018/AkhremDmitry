package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.dao.Employee;
import com.epam.brest.course.dao.EmployeeDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;


/**
 * Employee service.
 */
public class EmployeeServiceImp implements EmployeeService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * EmployeeDao.
     */
    private EmployeeDao employeeDao;

    /**
     * EmployeeDao.
     */
    private DepartmentDao departmentDao;

    /**
     * Setter for EmployeeDao.
     * @param employeeDao EmployeeDao.
     */
    public final void setEmployeeDao(final EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public final Collection<Employee> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }

    @Override
    public final Collection<Employee> getAllEmployeeWhere(final int minSalary,
                                              final int maxSalary) {
        LOGGER.debug("getAllEmployeeWhere minSalary = {}, maxSalary = {}",
                minSalary, maxSalary);
        String whereSql = " WHERE salary >= :minSalary "
                + "AND salary <= :maxSalary";
        Collection<Employee> employees = employeeDao
                .getAllEmployeeWhere(whereSql, minSalary, maxSalary);
        LOGGER.debug("getAllEmployeeWhere {}", employees);
        return employees;
    }


    @Override
    public final Employee getEmployeeById(final int employeeId) {
        LOGGER.debug("getEmployeeById({})", employeeId);
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public final Employee addEmployee(final Employee employee) {
        LOGGER.debug("addEmployee({})", employee);
        if (employee.getEmployeeName().matches(".*[^a-zA-Zа-яА-Я].*")) {
            throw new IllegalArgumentException("Department "
                    + "name can contain only letters");
        }
        return employeeDao.addEmployee(employee);
    }

    @Override
    public final void updateEmployeeSalary(final int employeeId,
                                           final int salary) {
        LOGGER.debug("updateEmployeeSalary(employeeId = {},"
                + " salary = {} )", employeeId, salary);
        Employee employee = employeeDao.getEmployeeById(employeeId);
        employee.setSalary(salary);
        employeeDao.updateEmployee(employee);
    }

    @Override
    public final void deleteEmployeeById(final int employeeId) {
        LOGGER.debug("deleteEmployeeById({})", employeeId);
        Employee employee = employeeDao.getEmployeeById(employeeId);
        employeeDao.deleteEmployeeById(employeeId);
        if (employeeDao.getNumberEmployeesInDepartment(employee.getDepartmentId())==0){
           departmentDao.deleteDepartmentById(employee.getDepartmentId());
        }
    }
}
