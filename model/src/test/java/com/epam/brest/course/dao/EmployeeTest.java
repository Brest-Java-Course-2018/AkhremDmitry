package com.epam.brest.course.dao;

import org.junit.Assert;
import org.junit.Test;

public class EmployeeTest {

    public static final String VASIA = "Vasia";
    public static final int ID = 1;

    @Test
    public void getEmployeeName() {
        Employee employee = new Employee();
        employee.setEmployeeName(VASIA);
        Assert.assertTrue(employee.getEmployeeName().equals(VASIA));
        Assert.assertEquals(VASIA,employee.getEmployeeName());
    }

    @Test
    public void getEmployeeId() {
        Employee employee = new Employee();
        employee.setEmployeeId(ID);
        Assert.assertTrue(employee.getEmployeeId()==ID);
    }
}
