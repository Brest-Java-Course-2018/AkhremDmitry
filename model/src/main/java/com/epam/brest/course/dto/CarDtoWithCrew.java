package com.epam.brest.course.dto;

/**
 * POJO CarDtoWithCrew.
 */
public class CarDtoWithCrew {

    /**
     * Property id.
     */
    private int carId;

    /**
     * Property registration plate.
     */
    private String registrationPlate;

    /**
     * Some description.
     */
    private String description;

    /**
     * Property number of crew.
     */
    private int numberOfCrew;

    /**
     * Constructor CarDtoWithCrew.
     */
    public CarDtoWithCrew() {
    }

    /**
     * Constructor CarDtoWithCrew.
     *
     * @param registrationPlate Car's registration plate.
     * @param description       Some description.
     * @param numberOfCrew      number of crew.
     */
    public CarDtoWithCrew(String registrationPlate,
                          String description,
                          int numberOfCrew) {
        this.registrationPlate = registrationPlate;
        this.description = description;
        this.numberOfCrew = numberOfCrew;
    }

    /**
     * det car id.
     *
     * @return carId.
     */
    public int getCarId() {
        return carId;
    }

    /**
     * Set car id.
     *
     * @param carId carId.
     */
    public void setCarId(int carId) {
        this.carId = carId;
    }

    /**
     * Get registration plate.
     *
     * @return registration plate.
     */
    public String getRegistrationPlate() {
        return registrationPlate;
    }

    /**
     * Set registration plate.
     *
     * @param registrationPlate registrationPlate.
     */
    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    /**
     * Get description.
     *
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description.
     *
     * @param description description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get number of crew.
     * @return number of crew.
     */
    public int getNumberOfCrew() {
        return numberOfCrew;
    }

    /**
     * Set number of crew.
     * @param numberOfCrew number of crew.
     */
    public void setNumberOfCrew(int numberOfCrew) {
        this.numberOfCrew = numberOfCrew;
    }

    @Override
    public String toString() {
        return "CarDtoWithCrew{" +
                "carId=" + carId +
                ", registrationPlate='" + registrationPlate + '\'' +
                ", description='" + description + '\'' +
                ", numberOfCrew=" + numberOfCrew +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarDtoWithCrew that = (CarDtoWithCrew) o;

        if (carId != that.carId) return false;
        if (numberOfCrew != that.numberOfCrew) return false;
        if (registrationPlate != null ? !registrationPlate.equals(that.registrationPlate) : that.registrationPlate != null)
            return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = carId;
        result = 31 * result + (registrationPlate != null ? registrationPlate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + numberOfCrew;
        return result;
    }
}
