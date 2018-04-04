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

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/carsDto")
    public final Collection<CarDto> getAllCarsDto(){
        LOGGER.debug("getAllCarsDto()");
        return carService.getAllCarsDto();
    }

    @GetMapping(value = "/cars/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final Car getCarById(@PathVariable final int id) {
        LOGGER.debug("getCarById({})", id);
        return carService.getCarById(id);
    }

    @PostMapping(value = "/cars")
    @ResponseStatus(HttpStatus.CREATED)
    public final Car addCar(@RequestBody final Car car) {
        LOGGER.debug("addCar({})", car);
        return carService.addCar(car);
    }

    @PutMapping(value = "/cars")
    public final void updateCar(@RequestBody final Car car) {
        LOGGER.debug("updateCar({})", car);
        carService.updateCar(car);
    }

    @DeleteMapping(value = "/cars/{id}")
    public final void deleteCarById(@PathVariable final int id) {
        LOGGER.debug("deleteCarById({})", id);
        carService.deleteCarById(id);
    }

    @GetMapping(value = "/cars")
    public final Collection<CarDtoWithCrew> getAllCarsDtoWithCrew() {
        LOGGER.debug("getAllCarsDtoWithCrew()");
        return carService.getAllCarsDtoWithCrew();
    }
}
