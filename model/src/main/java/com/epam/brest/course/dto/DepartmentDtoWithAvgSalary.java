package com.epam.brest.course.dto;

public class DepartmentDtoWithAvgSalary {
    /**
     * Property departmentId.
     */
    private int departmentId;

    /**
     * Property departmentName.
     */
    private String departmentName;

    /**
     * Property avgSalary.
     */
    private Integer avgSalary;

    /**
     * Get department id.
     * @return Integer
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * Set department id.
     * @param departmentId Integer
     */
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Get department name.
     * @return String
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Set department name.
     * @param departmentName String
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Get average salary.
     * @return int
     */
    public Integer getAvgSalary() {
        return avgSalary;
    }

    /**
     * Set average salary.
     * @param avgSalary int
     */
    public void setAvgSalary(Integer avgSalary) {
        this.avgSalary = avgSalary;
    }

    @Override
    public String toString() {
        return "DepartmentDtoWithAvgSalary{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", avgSalary=" + avgSalary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentDtoWithAvgSalary that = (DepartmentDtoWithAvgSalary) o;

        if (departmentId != that.departmentId) return false;
        if (avgSalary != that.avgSalary) return false;
        return departmentName != null ? departmentName.equals(that.departmentName) : that.departmentName == null;
    }

    @Override
    public int hashCode() {
        int result = departmentId;
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        result = 31 * result + avgSalary;
        return result;
    }
}
