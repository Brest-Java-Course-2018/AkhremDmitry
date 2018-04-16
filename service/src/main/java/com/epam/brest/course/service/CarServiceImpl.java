package com.epam.brest.course.service;

import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dao.CarDao;
import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CarDtoWithCrew;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * CarServiceImpl.
 */
@Service
public class CarServiceImpl implements CarService {

    /**
     * Property carDao.
     */
    @Autowired
    private CarDao carDao;

    /**
     * Logger init.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Setter for carDao.
     *
     * @param carDao DAO of car.
     */
    public final void setCarDao(final CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public final Collection<CarDto> getAllCarsDto() {
        LOGGER.debug("getAllCarsDto()");
        Collection<CarDto> cars = carDao.getAllCarsDto();
        return cars;
    }

    @Override
    public final Car getCarById(final int carId) {
        LOGGER.debug("getCarById({})", carId);
        Car car = carDao.getCarById(carId);
        return car;
    }

    @Override
    public final Car addCar(final Car car) {
        LOGGER.debug("addCar({})", car);
        if (carDao.checkCar(car.getRegistrationPlate()) != 0) {
            throw new IllegalArgumentException("Car with the same "
                    + "registration plate already exists in DB.");
        }
        return carDao.addCar(car);
    }

    @Override
    public final void updateCar(final Car car) {
        LOGGER.debug("updateCar({})", car);
        carDao.updateCar(car);
    }

    @Override
    public final void deleteCarById(final int id) {
        LOGGER.debug("deleteCarById({})", id);
        carDao.deleteCarById(id);
    }

    @Override
    public final int getNumberOfCars() {
        LOGGER.debug("getNumberOfCars()");
        return carDao.getNumberOfCars();
    }

    @Override
    public final Collection<CarDtoWithCrew> getAllCarsDtoWithCrew() {
        LOGGER.debug("getAllCarsDtoWithCrew()");
        return carDao.getAllCarsDtoWithCrew();
    }

}
