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
     * Property description.
     */
    private String description;

    /**
     * Constructor DepartmentDto.
     */
    public DepartmentDto() {
    }

    /**
     * Constructor DepartmentDto.
     *
     * @param departmentId id
     * @param departmentName name
     * @param description description department.
     */
    public DepartmentDto(final int departmentId,
                         final String departmentName,
                         final String description) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.description = description;
    }

    /**
     * Get department id.
     * @return Integer
     */
    public final Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Set department id.
     * @param departmentId Integer
     */
    public final void setDepartmentId(final Integer departmentId) {
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
     * Get description.
     * @return String
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Set description.
     * @param description String
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentDto that = (DepartmentDto) o;

        if (departmentId != that.departmentId) return false;
        if (departmentName != null ? !departmentName.equals(that.departmentName) : that.departmentName != null)
            return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = departmentId;
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
