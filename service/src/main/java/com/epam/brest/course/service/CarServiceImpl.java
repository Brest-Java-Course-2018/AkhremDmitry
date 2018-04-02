package com.epam.brest.course.service;

import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dao.CarDao;
import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CarDtoWithCrew;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public class CarServiceImpl implements CarService {

    private CarDao carDao;

    private static final Logger LOGGER = LogManager.getLogger();

    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public Collection<CarDto> getAllCarsDto() {
        LOGGER.debug("getAllCarsDto()");
        Collection<CarDto> cars = carDao.getAllCarsDto();
        return cars;
    }

    @Override
    public Car getCarById(int carId) {
        LOGGER.debug("getCarById({})", carId);
        Car car = carDao.getCarById(carId);
        return car;
    }

    @Override
    public final Car addCar(final Car car) {
        LOGGER.debug("addCar({})", car);
        if (carDao.checkCar(car.getRegistrationPlate()) != 0){
            throw new IllegalArgumentException("Car with the same "
                    + "registration plate already exists in DB.");
        }
        return carDao.addCar(car);
    }

    @Override
    public void updateCar(Car car) {
        LOGGER.debug("updateCar({})", car);
        carDao.updateCar(car);
    }

    @Override
    public void deleteCarById(int id) {
        LOGGER.debug("deleteCarById({})", id);
        carDao.deleteCarById(id);
    }

    @Override
    public int getNumberOfCars() {
        LOGGER.debug("getNumberOfCars()");
        return carDao.getNumberOfCars();
    }

    @Override
    public Collection<CarDtoWithCrew> getAllCarsDtoWithCrew() {
        LOGGER.debug("getAllCarsDtoWithCrew()");
        return carDao.getAllCarsDtoWithCrew();
    }

}
