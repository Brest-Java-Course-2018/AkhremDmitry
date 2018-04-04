package com.epam.brest.course.client.rest;

import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CarDtoWithCrew;
import com.epam.brest.course.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

public class CarRestClient implements CarService {

    private String url;

    private RestTemplate restTemplate;

    private static final Logger LOGGER = LogManager.getLogger();

    public CarRestClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public final Collection<CarDto> getAllCarsDto() {
        LOGGER.debug("getAllCarsDto()");
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url + "Dto", List.class);
        List<CarDto> cars = (List<CarDto>) responseEntity.getBody();
        return cars;
    }

    @Override
    public final Car getCarById(final int carId) {
        LOGGER.debug("getCarById({})", carId);
        ResponseEntity<Car> responseEntity =
                restTemplate.getForEntity(url + "/" + carId, Car.class);
        Car car = responseEntity.getBody();
        return car;
    }

    @Override
    public Car addCar(Car car) {
        LOGGER.debug("addCar({})", car);
        ResponseEntity<Car> responseEntity =
                restTemplate.postForEntity(url, car, Car.class);
        Car resultCar = responseEntity.getBody();
        return resultCar;
    }

    @Override
    public void updateCar(Car car) {
        LOGGER.debug("updateCar({})", car);
        restTemplate.put(url, car);
    }

    @Override
    public void deleteCarById(int id) {
        LOGGER.debug("deleteCarById({})", id);
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public int getNumberOfCars() {
        LOGGER.debug("getNumberOfCars()");
        return restTemplate.getForObject(url + "Num", Integer.class);
    }

    @Override
    public Collection<CarDtoWithCrew> getAllCarsDtoWithCrew() {
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url, List.class);
        List<CarDtoWithCrew> cars = (List<CarDtoWithCrew>) responseEntity.getBody();
        return cars;
    }
}
