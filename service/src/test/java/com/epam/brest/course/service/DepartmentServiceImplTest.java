package com.epam.brest.course.service;

import com.epam.brest.course.dao.Department;
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

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:service-test.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class DepartmentServiceImplTest {

    private static final int ID = 1;
    private static final String DESC = "Academic Department";

    @Autowired
    private DepartmentService departmentService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void updateDepartmentDescription() {
        departmentService.updateDepartmentDescription(ID, DESC);

        Department department = departmentService.getDepartmentById(ID);
        Assert.assertEquals(DESC, department.getDescription());
    }

    @Test
    public void getAllDepartment() {
        List<Department> departments = departmentService.getAllDepartment();
        Assert.assertFalse(departments.isEmpty());
        Assert.assertTrue(1 == departments.size());

        Department departmentExp = departmentService.getDepartmentById(1);
        Department departmentAct = departments.get(0);
        Assert.assertEquals(departmentExp, departmentAct);
    }

    @Test
    public void addDepartment() {
        int depCountExp = departmentService.getAllDepartment().size() + 1;
        Department departmentExp = new Department("Academic", DESC);
        Department departmentAct = departmentService.addDepartment(departmentExp);
        int depCountAct = departmentService.getAllDepartment().size();

        Assert.assertEquals(depCountExp, depCountAct);
        Assert.assertEquals(departmentExp.getDepartmentName(), departmentAct.getDepartmentName());
        Assert.assertEquals(departmentExp.getDescription(), departmentAct.getDescription());
    }

    @Test
    public void deleteDepartmentById() {
        int depCountExp = departmentService.getAllDepartment().size();
        Department department = new Department("Academic", DESC);
        department = departmentService.addDepartment(department);
        departmentService.deleteDepartmentById(department.getDepartmentId());
        int depCountAct = departmentService.getAllDepartment().size();

        Assert.assertEquals(depCountExp, depCountAct);
    }

    @Test
    public void getDepartmentById() {
        Department departmentExp = departmentService
                .addDepartment(new Department("Academic", DESC));
        Department departmentAct = departmentService
                .getDepartmentById(departmentExp.getDepartmentId());

        Assert.assertNotNull(departmentAct);
        Assert.assertEquals(departmentExp, departmentAct);
    }

    @Test
    public void addWrongDepartmentWithRule() {
        Department department = new Department("Academic#2", DESC);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Department name can contain only letters, numbers and underline");
        departmentService.addDepartment(department);
    }
}
