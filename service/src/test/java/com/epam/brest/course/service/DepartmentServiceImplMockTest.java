package com.epam.brest.course.service;

import com.epam.brest.course.dao.Department;
import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.dao.EmployeeDao;
import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class DepartmentServiceImplMockTest {

    private static final int ID = 1;
    private static final Department DEPARTMENT =
            new Department("Distribution", "Distribution department");

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentDao mockDepartmentDao;

    @Autowired
    private EmployeeDao mockEmployeeDao;

    @Before
    public void before() {
        EasyMock.reset(mockDepartmentDao);
        EasyMock.reset(mockEmployeeDao);
    }

    @Test
    public void getDepartmentById() {
        EasyMock.expect(mockDepartmentDao.getDepartmentById(ID)).andReturn(DEPARTMENT);

        EasyMock.replay(mockDepartmentDao);

        departmentService.getDepartmentById(ID);
        EasyMock.verify(mockDepartmentDao);
    }

    @Test
    public void getAllDepartmentDto() {
        Collection<DepartmentDto> departments = new ArrayList<>();
        EasyMock.expect(mockDepartmentDao.getAllDepartmentDto()).andReturn(departments);

        EasyMock.replay(mockDepartmentDao);

        departmentService.getAllDepartmentDto();
        EasyMock.verify(mockDepartmentDao);
    }

    @Test
    public void getAllDepartmentWithAvgSalary(){
        Collection<DepartmentDtoWithAvgSalary> departments = new ArrayList<>();
        EasyMock.expect(mockDepartmentDao.getAllDepartmentWithAvgSalary()).andReturn(departments);

        EasyMock.replay(mockDepartmentDao);

        departmentService.getAllDepartmentWithAvgSalary();
        EasyMock.verify(mockDepartmentDao);
    }

    @Test
    public void addDepartment() {
        EasyMock.expect(mockDepartmentDao.addDepartment(DEPARTMENT)).andReturn(DEPARTMENT);

        EasyMock.replay(mockDepartmentDao);

        departmentService.addDepartment(DEPARTMENT);
        EasyMock.verify(mockDepartmentDao);
    }

    @Test
    public void updateDepartment(){
        mockDepartmentDao.updateDepartment(DEPARTMENT);
        EasyMock.expectLastCall();

        EasyMock.replay(mockDepartmentDao);

        departmentService.updateDepartment(DEPARTMENT);
        EasyMock.verify(mockDepartmentDao);
    }

    @Test
    public void deleteDepartmentById() {
        mockDepartmentDao.deleteDepartmentById(ID);
        EasyMock.expectLastCall();
        EasyMock.expect (mockEmployeeDao.getNumberEmployeesInDepartment(ID)).andReturn(0);

        EasyMock.replay(mockDepartmentDao);
        EasyMock.replay(mockEmployeeDao);

        departmentService.deleteDepartmentById(ID);
        EasyMock.verify(mockDepartmentDao);
        EasyMock.verify(mockEmployeeDao);
    }





}
