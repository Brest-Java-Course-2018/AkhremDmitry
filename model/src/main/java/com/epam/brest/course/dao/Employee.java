package com.epam.brest.course.dao;

/**
 * POJO Employee for model.
 */
public class Employee {
    private int employeeId;
    private String employeeName;
    private int salary;
    private int departmentId;

    public Employee() {
    }

    public Employee(String employeeName, int salary, int departmentId) {
        this.employeeName = employeeName;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    /**
     * Get Employee id.
     * @return employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (employeeId != employee.employeeId) return false;
        if (salary != employee.salary) return false;
        if (departmentId != employee.departmentId) return false;
        return employeeName != null ? employeeName.equals(employee.employeeName) : employee.employeeName == null;
    }

    @Override
    public int hashCode() {
        int result = employeeId;
        result = 31 * result + (employeeName != null ? employeeName.hashCode() : 0);
        result = 31 * result + salary;
        result = 31 * result + departmentId;
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", departmentId=" + departmentId +
                '}';
    }
}
