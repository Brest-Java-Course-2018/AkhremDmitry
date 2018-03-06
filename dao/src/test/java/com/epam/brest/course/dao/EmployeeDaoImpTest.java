package com.epam.brest.course.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:test-dao.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class EmployeeDaoImpTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void getAllEmployee() {
        List<Employee> employees = employeeDao.getAllEmployee();
        Assert.assertFalse(employees.isEmpty());
    }

    @Test
    public void getEmployeeById() {
        Employee employee = employeeDao.getEmployeeById(1);

        Assert.assertNotNull(employee);
        Assert.assertEquals(1, employee.getEmployeeId());
        Assert.assertEquals("Kartman", employee.getEmployeeName());
        Assert.assertEquals(1000, employee.getSalary());
        Assert.assertEquals(1, employee.getDepartmentId());
    }

    @Test
    public void addEmployee() {
        List<Employee> employees = employeeDao.getAllEmployee();
        int sizeBefore = employees.size();
        Employee employeeExp =
                new Employee("Kenni", 1150, 1);
        Employee employeeAct = employeeDao.addEmployee(employeeExp);
        int sizeAfter = employeeDao.getAllEmployee().size();

        Assert.assertNotNull(employeeAct.getEmployeeId());
        Assert.assertEquals(employeeExp.getEmployeeName(), employeeAct.getEmployeeName());
        Assert.assertEquals(employeeExp.getSalary(), employeeAct.getSalary());
        Assert.assertEquals(employeeExp.getDepartmentId(), employeeAct.getDepartmentId());
        Assert.assertTrue((sizeAfter-1)==sizeBefore);
    }

    @Test
    public void updateEmployee() {
        Employee employee =
                new Employee("Stan", 1500, 1);
        employee = employeeDao.addEmployee(employee);
        List<Employee> employees = employeeDao.getAllEmployee();
        int sizeBefore = employees.size();
        Employee employeeExp =
                new Employee("Kyle", 1600, 1);
        employeeExp.setEmployeeId(employee.getEmployeeId());
        employeeDao.updateEmployee(employeeExp);
        employees = employeeDao.getAllEmployee();
        int sizeAfter = employees.size();
        Employee employeeAct =
                employeeDao.getEmployeeById(employeeExp.getEmployeeId());

        Assert.assertTrue(sizeBefore == sizeAfter);
        Assert.assertEquals(employeeExp, employeeAct);


    }

    @Test
    public void deleteEmployeeById() {
        Employee employee =
                new Employee("Batters", 600, 1);
        employee = employeeDao.addEmployee(employee);
        List<Employee> employees = employeeDao.getAllEmployee();
        int sizeBefore = employees.size();
        employeeDao.deleteEmployeeById(employee.getEmployeeId());
        employees = employeeDao.getAllEmployee();
        int sizeAfter = employees.size();

        Assert.assertTrue((sizeBefore-1) == sizeAfter);
    }
}
