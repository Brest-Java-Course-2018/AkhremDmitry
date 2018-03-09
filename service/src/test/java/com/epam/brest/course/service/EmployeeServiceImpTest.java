package com.epam.brest.course.service;

import com.epam.brest.course.dao.Employee;
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
        "classpath:service-test.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class EmployeeServiceImpTest {

    @Autowired
    private EmployeeService employeeService;

    private final static String NAME = "Erick";
    private final static int SALARY = 800;
    private final static int DEPARTMENTID = 1;

    @Test
    public void getAllEmployee(){
        List<Employee> employees = employeeService.getAllEmployee();
        Assert.assertFalse(employees.isEmpty());
    }

    @Test
    public void getAllEmployeeWhere() {
        Employee employeeExp1 = new Employee("Stan", 1500, 1);
        Employee employeeExp2 = new Employee("Kyle", 1100, 1);
        Employee employeeExp3 = new Employee("Kenni", 1200, 1);
        employeeService.addEmployee(employeeExp1);
        employeeService.addEmployee(employeeExp2);
        employeeService.addEmployee(employeeExp3);

        List<Employee> employees = employeeService.getAllEmployeeWhere(1100,1499);

        Assert.assertEquals(2, employees.size());
        Assert.assertTrue(employees.get(0).getSalary() >= 1100 && employees.get(0).getSalary() <= 1499);
        Assert.assertTrue(employees.get(1).getSalary() >= 1100 && employees.get(1).getSalary() <= 1499);
        Assert.assertNotEquals(employees.get(0),employees.get(1));
    }

    @Test
    public void getEmployeeById() {
        Employee employeeExp = employeeService
                .addEmployee(new Employee(NAME, SALARY, DEPARTMENTID));
        Employee employeeAct = employeeService.getEmployeeById(employeeExp.getEmployeeId());

        Assert.assertEquals(employeeExp, employeeAct);
    }

    @Test
    public void addEmployee() {
        List<Employee> employees = employeeService.getAllEmployee();
        int empCountExp = employees.size()+1;
        Employee employee = employeeService.addEmployee(new Employee(NAME, SALARY, DEPARTMENTID));
        int empCountAct = employeeService.getAllEmployee().size();

        Assert.assertTrue(empCountExp == empCountAct);
        Assert.assertEquals(NAME, employee.getEmployeeName());
        Assert.assertEquals(SALARY, employee.getSalary());
        Assert.assertEquals(DEPARTMENTID, employee.getDepartmentId());
    }

    @Test
    public void updateEmployeeSalary() {
        int salaryExp = 1200;
        Employee employee = employeeService
                .addEmployee(new Employee(NAME, SALARY, DEPARTMENTID));
        employeeService.updateEmployeeSalary(employee.getEmployeeId(), salaryExp);
        Employee employeeAct = employeeService.getEmployeeById(employee.getEmployeeId());


        Assert.assertEquals(salaryExp, employeeAct.getSalary());

    }

    @Test
    public void deleteEmployeeById() {
        Employee employee = new Employee(NAME, SALARY, DEPARTMENTID);
        employee = employeeService.addEmployee(employee);
        List<Employee> employees = employeeService.getAllEmployee();
        int empCountExp = employees.size()-1;
        employeeService.deleteEmployeeById(employee.getEmployeeId());
        int empCountAct = employeeService.getAllEmployee().size();

        Assert.assertTrue(empCountExp == empCountAct);
    }
}
