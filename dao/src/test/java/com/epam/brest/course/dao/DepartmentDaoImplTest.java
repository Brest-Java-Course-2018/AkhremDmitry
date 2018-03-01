package com.epam.brest.course.dao;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:test-dao.xml"})
public class DepartmentDaoImplTest {


    DepartmentDao departmentDao;

    @Before
    public void init(){
        DataSource dataSource = new ClassPathXmlApplicationContext("classpath:test-db-spring.xml")
                .getBean("dataSource", DataSource.class);
        departmentDao = new DepartmentDaoImpl(dataSource);

    }

    public void print(String nameMetods){
        System.out.println(nameMetods+":");
        for(Department curDepartment :departmentDao.getAllDepartment()){
            System.out.println(curDepartment);
        }
        System.out.println();
    }

    @Test
    public void getDepartmentById() {
        Department department = departmentDao.getDepartmentById(1);
        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId().equals(1));
        Assert.assertTrue(department.getDepartmentName().equals("Distribution"));
        Assert.assertTrue(department.getDescription().equals("Distribution department"));
        print("getDepartmentByI");
    }

    @Test
    public void getAllDepartment() {
        List<Department> departments = departmentDao.getAllDepartment();
        Assert.assertFalse(departments.isEmpty());
        print("getAllDepartment");
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
        print("addDepartment");
    }

    @Test
    public void updateDepartment() {
        Department departmentExp = new Department();
        departmentExp.setDepartmentId(1);
        departmentExp.setDepartmentName("Java");
        departmentExp.setDescription("Java Department");
        departmentDao.updateDepartment(departmentExp);
        Department departmentAct = departmentDao.getDepartmentById(1);

        Assert.assertEquals(departmentExp, departmentAct);
        print("updateDepartment");


    }

    @Test
    public void deleteDepartmentById() {
        departmentDao.deleteDepartmentById(1);
        Department departmentAct = departmentDao.getDepartmentById(1);
        Assert.assertNull(departmentAct);
        print("deleteDepartment");

    }


}
