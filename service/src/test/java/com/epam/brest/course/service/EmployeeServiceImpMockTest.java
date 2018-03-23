package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.dao.Employee;
import com.epam.brest.course.dao.EmployeeDao;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class EmployeeServiceImpMockTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDao mockEmployeeDao;

    @Autowired
    private DepartmentDao mockDepartmentDao;

    private static final String NAME = "Kenny";
    private static final String EMAIL = "Kenny@gmail.com";
    private static final int SALARY = 450;
    private static final int DEPARTMENTID = 1;
    private static final int ID = 1;
    private static final Employee EMPLOYEE = new Employee(NAME, EMAIL, SALARY, DEPARTMENTID);

    @BeforeClass
    public static void beforeClass(){
        EMPLOYEE.setEmployeeId(ID);
    }

    @Before
    public void before(){
        EasyMock.reset(mockEmployeeDao);
        EasyMock.reset(mockDepartmentDao);
    }

    @Test
    public void updateEmployeeSalaryTest(){
        int expectedSalary = 800;
        EasyMock.expect(mockEmployeeDao.getEmployeeById(ID)).andReturn(EMPLOYEE);

        Capture<Employee> captureArgument = Capture.newInstance();
        mockEmployeeDao.updateEmployee(EasyMock.capture(captureArgument));
        EasyMock.expectLastCall().times(1);

        EasyMock.replay(mockEmployeeDao);

        employeeService.updateEmployeeSalary(ID, expectedSalary);

        Employee employeeAct = captureArgument.getValue();

        Assert.assertEquals(expectedSalary, employeeAct.getSalary());
        EasyMock.verify(mockEmployeeDao);
    }

    @Test
    public void deleteEmployeeByIdTest(){
        EasyMock.expect(mockEmployeeDao.getEmployeeById(ID)).andReturn(EMPLOYEE);
        mockEmployeeDao.deleteEmployeeById(ID);
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockEmployeeDao.getNumberEmployeesInDepartment(DEPARTMENTID)).andReturn(0);
        mockDepartmentDao.deleteDepartmentById(ID);
        EasyMock.expectLastCall();

        EasyMock.replay(mockEmployeeDao);
        EasyMock.replay(mockDepartmentDao);

        employeeService.deleteEmployeeById(ID);
        EasyMock.verify(mockEmployeeDao);
        EasyMock.verify(mockDepartmentDao);
    }

    @Test
    public void getEmployeeById(){
        EasyMock.expect(mockEmployeeDao.getEmployeeById(ID)).andReturn(EMPLOYEE).times(1);

        EasyMock.replay(mockEmployeeDao);

        employeeService.getEmployeeById(ID);
        EasyMock.verify(mockEmployeeDao);
    }

    @Test
    public void getAllEmployee(){
        List<Employee> employees = new ArrayList<>();
        employees.add(EMPLOYEE);
        EasyMock.expect(mockEmployeeDao.getAllEmployee()).andReturn(employees).times(1);

        EasyMock.replay(mockEmployeeDao);

        employeeService.getAllEmployee();
        EasyMock.verify(mockEmployeeDao);
    }

    @Test
    public void getAllEmployeeWhere(){
        List<Employee> employees = new ArrayList<>();
        employees.add(EMPLOYEE);
        String whereSql = " WHERE salary >= :minSalary "
                + "AND salary <= :maxSalary";
        int minSalary = 500;
        int maxSalary = 1100;
        EasyMock.expect(mockEmployeeDao.getAllEmployeeWhere(whereSql, minSalary, maxSalary)).andReturn(employees).times(1);

        EasyMock.replay(mockEmployeeDao);

        employeeService.getAllEmployeeWhere(minSalary, maxSalary);
        EasyMock.verify(mockEmployeeDao);
    }

    @Test
    public void addEmployee(){
        EasyMock.expect(mockEmployeeDao.addEmployee(EMPLOYEE)).andReturn(EMPLOYEE).times(1);

        EasyMock.replay(mockEmployeeDao);

        employeeService.addEmployee(EMPLOYEE);
        EasyMock.verify(mockEmployeeDao);
    }

}
