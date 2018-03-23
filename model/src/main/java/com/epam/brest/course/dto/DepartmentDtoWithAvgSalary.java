package com.epam.brest.course.dto;

/**
 * POJO DepartmentDtoWithAvgSalary.
 */
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
     * Constructor DepartmentDtoWithAvgSalary.
     */
    public DepartmentDtoWithAvgSalary() {
    }

    /**
     * Constructor DepartmentDtoWithAvgSalary.
     *
     * @param departmentId int
     * @param departmentName String
     * @param avgSalary int
     */
    public DepartmentDtoWithAvgSalary(int departmentId, String departmentName, Integer avgSalary) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.avgSalary = avgSalary;
    }

    /**
     * Get department id.
     * @return Integer
     */
    public final int getDepartmentId() {
        return departmentId;
    }

    /**
     * Set department id.
     * @param departmentId Integer
     */
    public final void setDepartmentId(final int departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Get department name.
     * @return String
     */
    public final String getDepartmentName() {
        return departmentName;
    }

    /**
     * Set department name.
     * @param departmentName String
     */
    public final void setDepartmentName(final String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Get average salary.
     * @return int
     */
    public final Integer getAvgSalary() {
        return avgSalary;
    }

    /**
     * Set average salary.
     * @param avgSalary int
     */
    public final void setAvgSalary(final Integer avgSalary) {
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
