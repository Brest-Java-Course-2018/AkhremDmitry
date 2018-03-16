package com.epam.brest.course.service;

import com.epam.brest.course.dao.Department;
import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
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
    private static final String DESC = "Academic Department";
    private static final Department DEPARTMENT =
            new Department("Distribution", "Distribution department");

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentDao mockDepartmentDao;

    @After
    public void after() {
        EasyMock.reset(mockDepartmentDao);
    }

    @Test
    public void updateDepartmentDescription() {
        EasyMock.expect(mockDepartmentDao.getDepartmentById(EasyMock.anyInt()))
                .andReturn(DEPARTMENT);
        Capture<Department> captureArgument = Capture.newInstance();
        mockDepartmentDao.updateDepartment(EasyMock.capture(captureArgument));
        EasyMock.expectLastCall();
        EasyMock.replay(mockDepartmentDao);

        departmentService.updateDepartmentDescription(ID, DESC);

        Department department = captureArgument.getValue();

        Assert.assertEquals(DESC, department.getDescription());
        EasyMock.verify(mockDepartmentDao);
    }

    @Test
    public void getDepartmentById() {
        EasyMock.expect(mockDepartmentDao.getDepartmentById(ID)).andReturn(DEPARTMENT);

        EasyMock.replay(mockDepartmentDao);

        departmentService.getDepartmentById(ID);
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
    public void deleteDepartmentById() {
        mockDepartmentDao.deleteDepartmentById(ID);
        EasyMock.expectLastCall();

        EasyMock.replay(mockDepartmentDao);

        departmentService.deleteDepartmentById(ID);
        EasyMock.verify(mockDepartmentDao);
    }

    @Test
    public void getAllDepartment() {
        Collection<Department> departments = new ArrayList<>();
        departments.add(DEPARTMENT);
        EasyMock.expect(mockDepartmentDao.getAllDepartment()).andReturn(departments);

        EasyMock.replay(mockDepartmentDao);

        departmentService.getAllDepartment();
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
    public void getDepartmentDtoById() {
        EasyMock.expect(mockDepartmentDao.getDepartmentById(ID)).andReturn(DEPARTMENT);

        EasyMock.replay(mockDepartmentDao);

        departmentService.getDepartmentDtoById(ID);
        EasyMock.verify(mockDepartmentDao);
    }

}
