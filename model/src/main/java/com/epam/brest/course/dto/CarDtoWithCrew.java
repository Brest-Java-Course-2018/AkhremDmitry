package com.epam.brest.course.dto;

/**
 * POJO CarDtoWithCrew.
 */
public class CarDtoWithCrew extends AbstractCarDto{

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
    public CarDtoWithCrew(final String registrationPlate,
                          final String description,
                          final int numberOfCrew) {
        this.registrationPlate = registrationPlate;
        this.description = description;
        this.numberOfCrew = numberOfCrew;
    }

    /**
     * Get description.
     *
     * @return description.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Set description.
     *
     * @param description description.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get number of crew.
     * @return number of crew.
     */
    public final int getNumberOfCrew() {
        return numberOfCrew;
    }

    /**
     * Set number of crew.
     * @param numberOfCrew number of crew.
     */
    public final void setNumberOfCrew(final int numberOfCrew) {
        this.numberOfCrew = numberOfCrew;
    }

    @Override
    public String toString() {
        return "CarDtoWithCrew{" +
                "description='" + description + '\'' +
                ", numberOfCrew=" + numberOfCrew +
                ", carId=" + carId +
                ", registrationPlate='" + registrationPlate + '\'' +
                '}';
    }
}
