package com.epam.brest.course.dao;

import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CarDtoWithCrew;

import java.util.Collection;

/**
 * Car DAO Interface.
 */
public interface CarDao {

    /**
     * Return all cars from database.
     * @return Collection CarsDto.
     */
    Collection<CarDto> getAllCarsDto();

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
    Car addCar(Car car);

    /**
     * Update car in database.
     * @param car car
     */
    void updateCar(Car car);

    /**
     * Remove car by id from database.
     * @param id car id
     */
    void deleteCarById(int id);

    /**
     * Return number of cars.
     * @return number of records in the table car.
     */
    int getNumberOfCars();

    /**
     * Return all cars DTO with number of crew.
     * @return Collection CarDtoWithCrew.
     */
    Collection<CarDtoWithCrew> getAllCarsDtoWithCrew();

    /**
     * Return 1 if the car exists in db and 0 in another case.
     * @param registrationPlate registration plate
     * @return number of car.
     */
    int checkCar(String registrationPlate);
}
