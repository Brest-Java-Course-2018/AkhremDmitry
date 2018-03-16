package com.epam.brest.course.dao;


import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:test-dao.xml", "classpath:dao.xml"})
@Rollback
@Transactional
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
        Collection<Department> departments = departmentDao.getAllDepartment();
        Assert.assertFalse(departments.isEmpty());
    }

    @Test
    public void addDepartment() {
        Collection<Department> departments = departmentDao.getAllDepartment();
        int sizeBefore = departments.size();
        Department department = new Department("Education and Training", "Description");
        Department newDepartment = departmentDao.addDepartment(department);
        Assert.assertNotNull(newDepartment.getDepartmentId());
        Assert.assertTrue(newDepartment.getDepartmentName().equals(department.getDepartmentName()));
        Assert.assertTrue(newDepartment.getDescription().equals(department.getDescription()));
        Assert.assertTrue((sizeBefore+1) == departmentDao.getAllDepartment().size());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addSameDepartmentWithRule() {
        Department department =
                new Department("Education", "Department of education");
        departmentDao.addDepartment(department);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Department with the same name already exists in DB.");
        departmentDao.addDepartment(department);
    }

    @Test
    public void updateDepartment() {
        Department department =
                new Department("Education", "Department of education");
        Department newDepartment = departmentDao.addDepartment(department);
        newDepartment.setDepartmentName("NEWEducation");
        newDepartment.setDescription("NEW Department of education");
        departmentDao.updateDepartment(newDepartment);
        Department updatedDepartment = departmentDao.getDepartmentById(newDepartment.getDepartmentId());
        Assert.assertTrue(newDepartment.getDepartmentId().equals(updatedDepartment.getDepartmentId()));
        Assert.assertTrue(newDepartment.getDepartmentName().equals(updatedDepartment.getDepartmentName()));
        Assert.assertTrue(newDepartment.getDescription().equals(updatedDepartment.getDescription()));

        Assert.assertEquals(newDepartment, updatedDepartment);
    }


    @Test
    public void deleteDepartmentById() {
        Department department =
                new Department("Education", "Department of education");
        department = departmentDao.addDepartment(department);
        Collection<Department> departments = departmentDao.getAllDepartment();
        int sizeBefore = departments.size();
        departmentDao.deleteDepartmentById(department.getDepartmentId());
        departments = departmentDao.getAllDepartment();
        int sizeAfter = departments.size();
        Assert.assertTrue((sizeAfter+1) == sizeBefore);
    }

    @Test
    public void getAllDepartmentWithAvgSalary() {
        Collection<DepartmentDtoWithAvgSalary> departments = departmentDao.getAllDepartmentWithAvgSalary();
        Assert.assertFalse(departments.isEmpty());
    }

}
