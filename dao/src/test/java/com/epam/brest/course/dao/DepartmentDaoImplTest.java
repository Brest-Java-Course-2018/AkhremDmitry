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
}
