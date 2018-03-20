package com.epam.brest.course.dao;

import javax.validation.constraints.Size;

/**
 * POJO Department for model.
 */
public class Department {

    /**
     * Property departmentId.
     */
    private int departmentId;

    /**
     * Property departmentName.
     */
    @Size(min = 3, message = "Department Name cannot contain less "
            + "than 3 characters.")
    @Size(max = 255, message = "Department Name cannot contain more "
            + "than 255 characters.")
    private String departmentName;

    /**
     * Property description.
     */
    @Size(max = 255, message = "Description cannot contain more "
            + "than 255 characters.")
    private String description;

    /**
     * Constructor Department.
     */
    public Department() {
    }

    /**
     * Constructor Department.
     * @param departmentName String
     * @param description String
     */
    public Department(final String departmentName, final String description) {
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
    public int hashCode() {
        int result = departmentId;
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (departmentId != that.departmentId) return false;
        if (departmentName != null ? !departmentName.equals(that.departmentName) : that.departmentName != null)
            return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public String toString() {
        return "Department{"
                + "departmentId=" + departmentId
                + ", departmentName='" + departmentName + '\''
                + ", description='" + description + '\''
                + '}';
    }


}
