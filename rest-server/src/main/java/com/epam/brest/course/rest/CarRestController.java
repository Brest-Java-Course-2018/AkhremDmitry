package com.epam.brest.course.rest;

import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CarDtoWithCrew;
import com.epam.brest.course.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CarRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CarService carService;

    //curl -v localhost:8090/carsDto
    @GetMapping(value = "/carsDto")
    public final Collection<CarDto> getAllCarsDto() {
        LOGGER.debug("getAllCarsDto()");
        return carService.getAllCarsDto();
    }

    //curl -v localhost:8090/cars/1
    @GetMapping(value = "/cars/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final Car getCarById(@PathVariable final int id) {
        LOGGER.debug("getCarById({})", id);
        return carService.getCarById(id);
    }

    //curl -H "Content-Type: application/json" -X POST -d '{"registrationPlate":"3333 AA-2","description":"Ambulance"}' -v localhost:8090/cars
    @PostMapping(value = "/cars")
    @ResponseStatus(HttpStatus.CREATED)
    public final Car addCar(@RequestBody final Car car) {
        LOGGER.debug("addCar({})", car);
        return carService.addCar(car);
    }

    //curl -H "Content-Type: application/json" -X PUT -d '{"carId":1,"registrationPlate":"3333 AA-2","description":"Ambulance"}' -v localhost:8090/cars
    @PutMapping(value = "/cars")
    public final void updateCar(@RequestBody final Car car) {
        LOGGER.debug("updateCar({})", car);
        carService.updateCar(car);
    }

    //curl -X "DELETE" localhost:8090/cars/1
    @DeleteMapping(value = "/cars/{id}")
    public final void deleteCarById(@PathVariable final int id) {
        LOGGER.debug("deleteCarById({})", id);
        carService.deleteCarById(id);
    }

    //curl -v localhost:8090/cars
    @GetMapping(value = "/cars")
    public final Collection<CarDtoWithCrew> getAllCarsDtoWithCrew() {
        LOGGER.debug("getAllCarsDtoWithCrew()");
        return carService.getAllCarsDtoWithCrew();
    }

    //curl -v localhost:8090/carsNum
    @GetMapping(value = "/carsNum")
    public final int getNumberOfCars() {
        LOGGER.debug("getNumberOfCars()");
        return carService.getNumberOfCars();
    }
}
