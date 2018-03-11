package com.epam.brest.course.dao;

/**
 * POJO Employee for model.
 */
public class Employee {

    /**
     * Property employeeId.
     */
    private int employeeId;

    /**
     * Property employeeName.
     */
    private String employeeName;

    /**
     * Property salary.
     */
    private int salary;

    /**
     * Property departmentId.
     */
    private int departmentId;

    /**
     * Constructor Employee.
     */
    public Employee() {
    }

    /**
     * Constructor Employee.
     * @param employeeName String
     * @param salary int
     * @param departmentId int
     */
    public Employee(final String employeeName,
                    final int salary,
                    final int departmentId) {
        this.employeeName = employeeName;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    /**
     * Get employee id.
     * @return employeeId
     */
    public final int getEmployeeId() {
        return employeeId;
    }

    /**
     * Set employee id.
     * @param employeeId int
     */
    public final void setEmployeeId(final int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Get employee name.
     * @return String
     */
    public final String getEmployeeName() {
        return employeeName;
    }

    /**
     * Set employee name.
     * @param employeeName String
     */
    public final void setEmployeeName(final String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * Get salary.
     * @return int
     */
    public final int getSalary() {
        return salary;
    }

    /**
     * Set salary.
     * @param salary int
     */
    public final void setSalary(final int salary) {
        this.salary = salary;
    }

    /**
     * Get department id.
     * @return int
     */
    public final int getDepartmentId() {
        return departmentId;
    }

    /**
     * Set department id.
     * @param departmentId int
     */
    public final void setDepartmentId(final int departmentId) {
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
        return "Employee{"
                + "employeeId=" + employeeId
                + ", employeeName='" + employeeName + '\''
                + ", salary=" + salary
                + ", departmentId=" + departmentId
                + '}';
    }
}
