package com.epam.brest.cource;


import org.junit.Assert;

public class EmployeeTest {

    public static final String VASIA = "Vasia";

    @org.junit.Test
    public void getEmployeeName() {
        Employee employee = new Employee();
        employee.setEmployeeName(VASIA);
        Assert.assertTrue(employee.getEmployeeName().equals(VASIA));
        Assert.assertEquals(VASIA,employee.getEmployeeName());
    }
}
