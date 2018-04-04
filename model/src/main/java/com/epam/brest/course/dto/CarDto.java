package com.epam.brest.course.dto;

/**
 * POJO CarDto.
 */
public class CarDto extends AbstractCarDto{

    /**
     * Constructor CarDto.
     */
    public CarDto() {
    }

    /**
     * Constructor CarDto.
     *
     * @param registrationPlate Car's registration plate.
     */
    public CarDto(final String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "carId=" + carId +
                ", registrationPlate='" + registrationPlate + '\'' +
                '}';
    }
}
