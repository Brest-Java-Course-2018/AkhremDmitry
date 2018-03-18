package com.epam.brest.course.dto;

public class DepartmentDto {
    /**
     * Property departmentId.
     */
    private int departmentId;

    /**
     * Property departmentName.
     */
    private String departmentName;

    /**
     * Constructor DepartmentDto.
     */
    public DepartmentDto() {
    }

    /**
     * Constructor DepartmentDto.
     *
     * @param departmentId   id
     * @param departmentName name
     */
    public DepartmentDto(final int departmentId,
                         final String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    /**
     * Get department id.
     *
     * @return Integer
     */
    public final Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Set department id.
     *
     * @param departmentId Integer
     */
    public final void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Get department name.
     *
     * @return String
     */
    public final String getDepartmentName() {
        return departmentName;
    }

    /**
     * Set department name.
     *
     * @param departmentName String
     */
    public final void setDepartmentName(final String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentDto that = (DepartmentDto) o;

        if (departmentId != that.departmentId) return false;
        return departmentName != null ? departmentName.equals(that.departmentName) : that.departmentName == null;
    }

    @Override
    public int hashCode() {
        int result = departmentId;
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        return result;
    }
}
