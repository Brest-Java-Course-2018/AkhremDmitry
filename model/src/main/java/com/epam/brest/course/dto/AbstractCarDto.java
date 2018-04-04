package com.epam.brest.course.dto;

/**
 * AbstractCarDto.
 */
public abstract class AbstractCarDto {

    /**
     * Property id.
     */
    protected int carId;

    /**
     * Property registration plate.
     */
    protected String registrationPlate;

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
    public void setCarId(final int carId) {
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
    public void setRegistrationPlate(final String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }
}
