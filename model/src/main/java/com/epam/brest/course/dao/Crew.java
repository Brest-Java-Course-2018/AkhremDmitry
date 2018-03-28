package com.epam.brest.course.dao;

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
    private String crewName;

    /**
     * Property description.
     */
    private String description;

    /**
     * Property carId.
     */
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
    public Crew(String crewName, String description, int carId) {
        this.crewName = crewName;
        this.description = description;
        this.carId = carId;
    }

    /**\
     * Get crew id.
     * @return Unique identifier.
     */
    public int getCrewId() {
        return crewId;
    }

    /**
     * Set crewId.
     * @param crewId Unique identifier.
     */
    public void setCrewId(int crewId) {
        this.crewId = crewId;
    }

    /**
     * Get crew name.
     * @return Crew name.
     */
    public String getCrewName() {
        return crewName;
    }

    /**
     * Set crew name.
     * @param crewName Crew name.
     */
    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    /**
     * Get description.
     * @return Some description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description.
     * @param description Some description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get car id.
     * @return Crew's car.
     */
    public int getCarId() {
        return carId;
    }

    /**
     * Set car id.
     * @param carId Crew's car.
     */
    public void setCarId(int carId) {
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
