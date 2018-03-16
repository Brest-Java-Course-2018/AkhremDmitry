package com.epam.brest.course.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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
        Collection<Employee> employees = employeeDao.getAllEmployee();
        Assert.assertFalse(employees.isEmpty());
    }

    @Test
    public void getEmployeeById() {
        Employee employee = employeeDao.getEmployeeById(1);

        Assert.assertNotNull(employee);
        Assert.assertEquals(1, employee.getEmployeeId());
        Assert.assertEquals("Kartman", employee.getEmployeeName());
        Assert.assertEquals("Kartman@gmail.com", employee.getEmployeeEmail());
        Assert.assertEquals(1000, employee.getSalary());
        Assert.assertEquals(1, employee.getDepartmentId());
    }

    @Test
    public void addEmployee() {
        Collection<Employee> employees = employeeDao.getAllEmployee();
        int sizeBefore = employees.size();
        Employee employeeExp =
                new Employee("Kenni", "Kenni@gmail.com", 1150, 1);
        Employee employeeAct = employeeDao.addEmployee(employeeExp);
        int sizeAfter = employeeDao.getAllEmployee().size();

        Assert.assertNotNull(employeeAct.getEmployeeId());
        Assert.assertEquals(employeeExp.getEmployeeName(), employeeAct.getEmployeeName());
        Assert.assertEquals(employeeExp.getEmployeeEmail(), employeeAct.getEmployeeEmail());
        Assert.assertEquals(employeeExp.getSalary(), employeeAct.getSalary());
        Assert.assertEquals(employeeExp.getDepartmentId(), employeeAct.getDepartmentId());
        Assert.assertTrue((sizeAfter - 1) == sizeBefore);
    }

    @Test
    public void updateEmployee() {
        Employee employee =
                new Employee("Stan", "Stan@gmail.com", 1500, 1);
        employee = employeeDao.addEmployee(employee);
        Collection<Employee> employees = employeeDao.getAllEmployee();
        int sizeBefore = employees.size();
        Employee employeeExp =
                new Employee("Kyle", "Kyle@gmail.com", 1600, 1);
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
                new Employee("Batters", "Batters@gmail.com", 600, 1);
        employee = employeeDao.addEmployee(employee);
        Collection<Employee> employees = employeeDao.getAllEmployee();
        int sizeBefore = employees.size();
        employeeDao.deleteEmployeeById(employee.getEmployeeId());
        employees = employeeDao.getAllEmployee();
        int sizeAfter = employees.size();

        Assert.assertTrue((sizeBefore - 1) == sizeAfter);
    }

    @Test
    public void getAllEmployeeByDepartmentId() {
        employeeDao.addEmployee(new Employee("Stan", "Stan@gmail.com", 1500, 2));
        employeeDao.addEmployee(new Employee("Batters", "Batters@gmail.com", 600, 2));
        employeeDao.addEmployee(new Employee("Kyle", "Kyle@gmail.com", 660, 1));
        Collection<Employee> employees = employeeDao.getAllEmployeeByDepartmentId(1);

        Assert.assertFalse(employees.isEmpty());
        Assert.assertEquals(2, employees.size());
        for (Employee curEmployee : employees) {
            Assert.assertEquals(1, curEmployee.getDepartmentId());
        }

    }

    @Test
    public void getAllEmployeeWhere() {
        String whereSql = " WHERE salary >= :minSalary "
                + "AND salary <= :maxSalary";
        Employee employeeExp1 = new Employee("Stan","Stan@gmail.com", 1500, 1);
        Employee employeeExp2 = new Employee("Kyle", "Kyle@gmail.com", 1100, 1);
        Employee employeeExp3 = new Employee("Kenni", "Kenni@gmail.com", 1200, 1);
        employeeDao.addEmployee(employeeExp1);
        employeeDao.addEmployee(employeeExp2);
        employeeDao.addEmployee(employeeExp3);

        List<Employee> employees = (List)employeeDao.getAllEmployeeWhere(whereSql, 1100, 1499);

        Assert.assertEquals(2, employees.size());
        Assert.assertTrue(employees.get(0).getSalary() >= 1100 && employees.get(0).getSalary() <= 1499);
        Assert.assertTrue(employees.get(1).getSalary() >= 1100 && employees.get(1).getSalary() <= 1499);
        Assert.assertNotEquals(employees.get(0), employees.get(1));
    }

    @Test
    public void getNumberEmployeesInDepartment() {
        employeeDao.addEmployee(new Employee("Stan", "Stan@gmail.com", 1500, 1));
        employeeDao.addEmployee(new Employee("Kyle", "Kyle@gmail.com", 1100, 2));
        employeeDao.addEmployee(new Employee("Kenni", "Kenni@gmail.com", 1200, 1));
        Assert.assertEquals(3, employeeDao.getNumberEmployeesInDepartment(1));
        Assert.assertEquals(1, employeeDao.getNumberEmployeesInDepartment(2));
        Assert.assertEquals(0, employeeDao.getNumberEmployeesInDepartment(3));
    }
}
