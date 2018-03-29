package com.epam.brest.course.dto;

/**
 * POJO CarDto.
 */
public class CarDto {

    /**
     * Property id.
     */
    private int carId;

    /**
     * Property registration plate.
     */
    private String registrationPlate;

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

    /**
     * det car id.
     *
     * @return carId.
     */
    public final int getCarId() {
        return carId;
    }

    /**
     * Set car id.
     *
     * @param carId carId.
     */
    public final void setCarId(final int carId) {
        this.carId = carId;
    }

    /**
     * Get registration plate.
     *
     * @return registration plate.
     */
    public final String getRegistrationPlate() {
        return registrationPlate;
    }

    /**
     * Set registration plate.
     *
     * @param registrationPlate registrationPlate.
     */
    public final void setRegistrationPlate(final String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "carId=" + carId +
                ", registrationPlate='" + registrationPlate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarDto carDto = (CarDto) o;

        if (carId != carDto.carId) return false;
        return registrationPlate != null ? registrationPlate.equals(carDto.registrationPlate) : carDto.registrationPlate == null;
    }

    @Override
    public int hashCode() {
        int result = carId;
        result = 31 * result + (registrationPlate != null ? registrationPlate.hashCode() : 0);
        return result;
    }
}
