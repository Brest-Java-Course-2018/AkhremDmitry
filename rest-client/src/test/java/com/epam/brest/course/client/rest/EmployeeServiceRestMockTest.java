package com.epam.brest.course.client.rest;

import com.epam.brest.course.dao.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-test.xml")
public class EmployeeServiceRestMockTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RestTemplate mockRestTemplate;

    private static Employee employee1;
    private static Employee employee2;

    @Before
    public void init() {
        employee1 = new Employee("felix", "felix@gmail.com", 1000, 2);
        employee2 = new Employee("Kenny", "Kenny@gmail.com", 2000, 3);
        employee1.setEmployeeId(1);
        employee2.setEmployeeId(2);
    }

    @After
    public void tearDown() {
        EasyMock.verify(mockRestTemplate);
        EasyMock.reset(mockRestTemplate);
    }

    @Test
    public void getAllEmployeeTest() {
        Collection<Employee> employees = Arrays.asList(employee1, employee2);
        ResponseEntity responseEntity = new ResponseEntity(employees, HttpStatus.OK);

        EasyMock.expect(mockRestTemplate.getForEntity("http://localhost:8090/employees",
                List.class))
                .andReturn(responseEntity);
        EasyMock.replay(mockRestTemplate);

        Collection<Employee> employeesAct = employeeService.getAllEmployee();

        Assert.assertNotNull(employeesAct);
        Assert.assertEquals(2, employeesAct.size());
    }

    @Test
    public void getEmployeeByIdTest() {
        ResponseEntity responseEntity = new ResponseEntity(employee1, HttpStatus.FOUND);

        EasyMock.expect(mockRestTemplate.getForEntity("http://localhost:8090/employees/1",
                Employee.class)).andReturn(responseEntity);
        EasyMock.replay(mockRestTemplate);

        Employee employeeAct = employeeService.getEmployeeById(1);

        Assert.assertNotNull(employeeAct);
        Assert.assertEquals(employee1, employeeAct);
    }

    @Test
    public void addEmployeeTest() {
        ResponseEntity responseEntity = new ResponseEntity(employee1, HttpStatus.CREATED);

        EasyMock.expect(mockRestTemplate.postForEntity("http://localhost:8090/employees",
                employee1, Employee.class)).andReturn(responseEntity);
        EasyMock.replay(mockRestTemplate);

        Employee employeeAct = employeeService.addEmployee(employee1);

        Assert.assertNotNull(employeeAct);
        Assert.assertEquals(employee1, employeeAct);
    }

    @Test
    public void deleteEmployeeByIdTest(){
        mockRestTemplate.delete("http://localhost:8090/employees/1");
        EasyMock.expectLastCall();
        EasyMock.replay(mockRestTemplate);

        employeeService.deleteEmployeeById(1);
    }

    @Test
    public void updateEmployeeByIdTest(){
        mockRestTemplate.put("http://localhost:8090/employees", employee1);
        EasyMock.expectLastCall();
        EasyMock.replay(mockRestTemplate);

        employeeService.updateEmployee(employee1);
    }

}
