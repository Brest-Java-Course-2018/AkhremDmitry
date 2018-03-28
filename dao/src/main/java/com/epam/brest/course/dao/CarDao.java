package com.epam.brest.course.dao;

import java.util.Collection;

/**
 * Car DAO Interface.
 */
public interface CarDao {

    /**
     * Return all cars from database.
     * @return Collection Cars.
     */
    Collection<Car> getAllCar();

    /**
     * Return car by id from database.
     * @param carId id
     * @return Car
     */
    Car getCarById(int carId);

    /**
     * Add car to database.
     * @param car new car
     * @return car with id
     */
    Car addCar (Car car);

    /**
     * Update car in database.
     * @param car car
     */
    void updateCar (Car car);

    /**
     * Remove car by id from database.
     * @param id car id
     */
    void deleteCarById (int id);
}
