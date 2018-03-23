package com.epam.brest.course.client.rest;

import com.epam.brest.course.dao.Department;
import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;
import com.epam.brest.course.service.DepartmentService;
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
public class DepartmentServiceRestMockTest {

    private static DepartmentDtoWithAvgSalary departmentDtoWithAvgSalary1;
    private static DepartmentDtoWithAvgSalary departmentDtoWithAvgSalary2;
    private static Department department;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RestTemplate mockRestTemplate;

    @Before
    public void init() {
        departmentDtoWithAvgSalary1 = new DepartmentDtoWithAvgSalary(1,"name1", 100);
        departmentDtoWithAvgSalary2= new DepartmentDtoWithAvgSalary(2,"name2", 200);
        department = new Department("name", "desc");
        department.setDepartmentId(3);
    }

    @After
    public void tearDown(){
        EasyMock.verify(mockRestTemplate);
        EasyMock.reset(mockRestTemplate);
    }

    @Test
    public void getAllDepartments(){
        List departments = Arrays.asList(departmentDtoWithAvgSalary1, departmentDtoWithAvgSalary2);
        ResponseEntity entity = new ResponseEntity<> (departments, HttpStatus.OK);

        EasyMock.expect(mockRestTemplate.getForEntity(EasyMock.anyString(), EasyMock.anyObject()))
                .andReturn(entity).times(1);
        EasyMock.replay(mockRestTemplate);


        Collection<DepartmentDtoWithAvgSalary> results
                = departmentService.getAllDepartmentWithAvgSalary();

        Assert.assertNotNull(departments);
        Assert.assertEquals(2, departments.size());
    }

    @Test
    public void getDepartmentById() {
        ResponseEntity entity = new ResponseEntity<> (department, HttpStatus.FOUND);
        EasyMock.expect(mockRestTemplate.getForEntity(EasyMock.anyString(), EasyMock.anyObject()))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Department result = departmentService.getDepartmentById(3);

        Assert.assertNotNull(result);
        Assert.assertEquals("name", result.getDepartmentName());
    }

    @Test
    public void addDepartment() {
        ResponseEntity entity = new ResponseEntity<> (department, HttpStatus.FOUND);
        EasyMock.expect(mockRestTemplate.postForEntity(EasyMock.anyString(), EasyMock.anyObject(), EasyMock.anyObject()))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Department result = departmentService.addDepartment(department);

        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.getDepartmentId().intValue());
    }


}
