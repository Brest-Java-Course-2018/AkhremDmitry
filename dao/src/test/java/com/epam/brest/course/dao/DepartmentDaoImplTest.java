package com.epam.brest.course.dao;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:test-dao.xml"})
public class DepartmentDaoImplTest {

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void getDepartmentById() {
        Department department = departmentDao.getDepartmentById(1);
        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId().equals(1));
        Assert.assertTrue(department.getDepartmentName().equals("Distribution"));
        Assert.assertTrue(department.getDescription().equals("Distribution department"));
    }

    @Test
    public void getAllDepartment() {
        List<Department> departments = departmentDao.getAllDepartment();
        Assert.assertFalse(departments.isEmpty());
    }

    @Test
    public void addDepartment() {
        Department departmentExp = new Department();
        departmentExp.setDepartmentName("Java");
        departmentExp.setDescription("Java Department");
        Department departmentAct = departmentDao.addDepartment(departmentExp);

        Assert.assertNotNull(departmentAct);
        Assert.assertEquals("Java", departmentAct.getDepartmentName());
        Assert.assertEquals("Java Department", departmentAct.getDescription());

        departmentAct = departmentDao.addDepartment(departmentExp);
        Assert.assertNull(departmentAct);
    }

    @Test
    public void updateDepartment() {
        Department departmentExp = new Department();
        departmentExp.setDepartmentId(2);
        departmentExp.setDepartmentName("Finance");
        departmentExp.setDescription("Finance Department");
        departmentDao.updateDepartment(departmentExp);
        Department departmentAct = departmentDao.getDepartmentById(2);

        Assert.assertEquals(departmentExp, departmentAct);
    }

    @Test
    public void getDepartmentByName() {
        String departmentNameExp = "Distribution";
        String departmentNameAct = departmentDao.getDepartmentByName("Distribution").getDepartmentName();
        Assert.assertEquals(departmentNameExp, departmentNameAct);

        Department departmentAct = departmentDao.getDepartmentByName("noName");
        Assert.assertNull(departmentAct);
    }

    @Test
    public void deleteDepartmentById() {
        departmentDao.deleteDepartmentById(3);
        Department departmentAct = departmentDao.getDepartmentById(3);
        Assert.assertNull(departmentAct);
    }

}
