package com.epam.brest.course.dao;

import javax.validation.constraints.*;

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
    @Size(min=3, message = "Employee Name cannot contain less than 3 characters.")
    @Size(max=255, message = "Employee Name cannot contain more than 255 characters.")
    private String employeeName;

    /**
     * Property employee Email.
     */
    @Email(message = "Please check the entered Email.")
    @NotEmpty(message = "Please enter the Email.")
    private String employeeEmail;

    /**
     * Property salary.
     */
    @Positive(message = "The salary should be greater than zero!")
    private int salary;

    /**
     * Property departmentId.
     */
    @Positive(message = "Please choose a department.")
    private int departmentId;

    /**
     * Constructor Employee.
     */
    public Employee() {
    }

    /**
     * Constructor Employee.
     *
     * @param employeeName  String
     * @param salary        int
     * @param departmentId  int
     * @param employeeEmail String.
     */
    public Employee(String employeeName, String employeeEmail, int salary, int departmentId) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    /**
     * Get employee Email.
     *
     * @return employeeEmail
     */
    public String getEmployeeEmail() {
        return employeeEmail;
    }

    /**
     * Set employee Email.
     *
     * @param employeeEmail String
     */
    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    /**
     * Get employee id.
     *
     * @return employeeId
     */
    public final int getEmployeeId() {
        return employeeId;
    }

    /**
     * Set employee id.
     *
     * @param employeeId int
     */
    public final void setEmployeeId(final int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Get employee name.
     *
     * @return String
     */
    public final String getEmployeeName() {
        return employeeName;
    }

    /**
     * Set employee name.
     *
     * @param employeeName String
     */
    public final void setEmployeeName(final String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * Get salary.
     *
     * @return int
     */
    public final int getSalary() {
        return salary;
    }

    /**
     * Set salary.
     *
     * @param salary int
     */
    public final void setSalary(final int salary) {
        this.salary = salary;
    }

    /**
     * Get department id.
     *
     * @return int
     */
    public final int getDepartmentId() {
        return departmentId;
    }

    /**
     * Set department id.
     *
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
        if (employeeName != null ? !employeeName.equals(employee.employeeName) : employee.employeeName != null)
            return false;
        return employeeEmail != null ? employeeEmail.equals(employee.employeeEmail) : employee.employeeEmail == null;
    }

    @Override
    public int hashCode() {
        int result = employeeId;
        result = 31 * result + (employeeName != null ? employeeName.hashCode() : 0);
        result = 31 * result + (employeeEmail != null ? employeeEmail.hashCode() : 0);
        result = 31 * result + salary;
        result = 31 * result + departmentId;
        return result;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "employeeId=" + employeeId
                + ", employeeName='" + employeeName + '\''
                + ", employeeEmail='" + employeeEmail + '\''
                + ", salary=" + salary
                + ", departmentId=" + departmentId
                + '}';
    }
}
