package com.epam.brest.course.dao;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * POJO Crew.
 */
public class Crew {
    /**
     * Property id.
     */
    private int crewId;

    /**
     * Property name.
     */
    @Size(min = 3, message = "Address cannot contain less "
            + "than 3 characters.")
    @Size(max = 255, message = "Address cannot contain more "
            + "than 255 characters.")
    private String crewName;

    /**
     * Property description.
     */
    private String description;

    /**
     * Property carId.
     */
    @Positive
    private int carId;

    /**
     * Constructor Crew.
     */
    public Crew() {
    }

    /**
     * Constryctor Crew.
     * @param crewName Crew name
     * @param description Some description
     * @param carId Crew's car.
     */
    public Crew(final String crewName,
                final String description,
                final int carId) {
        this.crewName = crewName;
        this.description = description;
        this.carId = carId;
    }

    /**
     * Get crew id.
     * @return Unique identifier.
     */
    public final int getCrewId() {
        return crewId;
    }

    /**
     * Set crewId.
     * @param crewId Unique identifier.
     */
    public final void setCrewId(final int crewId) {
        this.crewId = crewId;
    }

    /**
     * Get crew name.
     * @return Crew name.
     */
    public final String getCrewName() {
        return crewName;
    }

    /**
     * Set crew name.
     * @param crewName Crew name.
     */
    public final void setCrewName(final String crewName) {
        this.crewName = crewName;
    }

    /**
     * Get description.
     * @return Some description.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Set description.
     * @param description Some description.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get car id.
     * @return Crew's car.
     */
    public final int getCarId() {
        return carId;
    }

    /**
     * Set car id.
     * @param carId Crew's car.
     */
    public final void setCarId(final int carId) {
        this.carId = carId;
    }


    @Override
    public String toString() {
        return "Crew{" +
                "crewId=" + crewId +
                ", crewName='" + crewName + '\'' +
                ", description='" + description + '\'' +
                ", carId=" + carId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Crew crew = (Crew) o;

        if (crewId != crew.crewId) return false;
        if (carId != crew.carId) return false;
        if (crewName != null ? !crewName.equals(crew.crewName) : crew.crewName != null) return false;
        return description != null ? description.equals(crew.description) : crew.description == null;
    }

    @Override
    public int hashCode() {
        int result = crewId;
        result = 31 * result + (crewName != null ? crewName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + carId;
        return result;
    }
}
