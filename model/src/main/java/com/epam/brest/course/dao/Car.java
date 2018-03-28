package com.epam.brest.course.dao;

/**
 * POJO Car.
 */
public class Car {

    /**
     * Property id.
     */
    private String carId;

    /**
     * Property registration plate.
     */
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
    public String getCarId() {
        return carId;
    }

    /**
     * Set car id.
     * @param carId carId.
     */
    public void setCarId(String carId) {
        this.carId = carId;
    }

    /**
     * Get registration plate.
     * @return registration plate.
     */
    public String getRegistrationPlate() {
        return registrationPlate;
    }

    /**
     * Set registration plate.
     * @param registrationPlate registrationPlate.
     */
    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    /**
     * Get description.
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description.
     * @param description description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + carId + '\'' +
                ", registrationPlate='" + registrationPlate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (carId != null ? !carId.equals(car.carId) : car.carId != null) return false;
        if (registrationPlate != null ? !registrationPlate.equals(car.registrationPlate) : car.registrationPlate != null)
            return false;
        return description != null ? description.equals(car.description) : car.description == null;
    }

    @Override
    public int hashCode() {
        int result = carId != null ? carId.hashCode() : 0;
        result = 31 * result + (registrationPlate != null ? registrationPlate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
