package com.epam.brest.course.dao;

import javax.validation.constraints.Pattern;

/**
 * POJO Car.
 */
public class Car {

    /**
     * Property id.
     */
    private int carId;

    /**
     * Property registration plate.
     */
    @Pattern(regexp = "[0-9]{4} [A-Z]{2}-[1-7]",
            message = "Registration plate must be in the format: 1111 XX-1")
    private String registrationPlate;

    /**
     * Some description.
     */
    private String description;

    /**
     * Constructor Car.
     */
    public Car() {
    }

    /**
     * Constructor Car.
     * @param registrationPlate Car's registration plate.
     * @param description Some description.
     */
    public Car(final String registrationPlate,
               final String description) {
        this.registrationPlate = registrationPlate;
        this.description = description;
    }

    /**
     * det car id.
     * @return carId.
     */
    public final int getCarId() {
        return carId;
    }

    /**
     * Set car id.
     * @param carId carId.
     */
    public final void setCarId(final int carId) {
        this.carId = carId;
    }

    /**
     * Get registration plate.
     * @return registration plate.
     */
    public final String getRegistrationPlate() {
        return registrationPlate;
    }

    /**
     * Set registration plate.
     * @param registrationPlate registrationPlate.
     */
    public final void setRegistrationPlate(final String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    /**
     * Get description.
     * @return description.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Set description.
     * @param description description.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", registrationPlate='" + registrationPlate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (carId != car.carId) return false;
        if (registrationPlate != null ? !registrationPlate.equals(car.registrationPlate) : car.registrationPlate != null)
            return false;
        return description != null ? description.equals(car.description) : car.description == null;
    }

    @Override
    public int hashCode() {
        int result = carId;
        result = 31 * result + (registrationPlate != null ? registrationPlate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
