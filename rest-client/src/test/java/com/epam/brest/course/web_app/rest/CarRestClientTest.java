package com.epam.brest.course.web_app.rest;


import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CarDtoWithCrew;
import com.epam.brest.course.service.CarService;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rest-context.xml")
public class CarRestClientTest {

    @Autowired
    private CarService carService;

    @Autowired
    private RestTemplate mockRestTemplate;

    private Car expCar = new Car();

    @After
    public void after() {
        EasyMock.verify(mockRestTemplate);
        EasyMock.reset(mockRestTemplate);
    }

    @Before
    public void setUp(){
        expCar.setCarId(1);
        expCar.setRegistrationPlate("5555 AA-1");
        expCar.setDescription("Ambulance");
    }

    @Test
    public void getAllCarsDtoTest() {
        CarDto carDto = new CarDto("5555 AA-1");
        carDto.setCarId(1);
        List cars = Arrays.asList(carDto);
        ResponseEntity entity = new ResponseEntity(cars, HttpStatus.FOUND);

        EasyMock.expect(mockRestTemplate.getForEntity("http://localhost:8090/carsDto", List.class))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Collection<CarDto> results
                = carService.getAllCarsDto();

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void getCarByIdTest() {
        ResponseEntity entity = new ResponseEntity(expCar, HttpStatus.FOUND);

        EasyMock.expect(mockRestTemplate.getForEntity("http://localhost:8090/cars/1", Car.class))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Car actCar = carService.getCarById(1);

        Assert.assertEquals(expCar, actCar);
    }

    @Test
    public void addCarTest(){
        ResponseEntity responseEntity = new ResponseEntity(expCar, HttpStatus.OK);

        EasyMock.expect(mockRestTemplate
                .postForEntity("http://localhost:8090/cars", expCar, Car.class))
                .andReturn(responseEntity);
        EasyMock.replay(mockRestTemplate);

        Car actCar = carService.addCar(expCar);

        Assert.assertEquals(expCar, actCar);
    }

    @Test
    public void updateCarTest(){
        mockRestTemplate.put("http://localhost:8090/cars", expCar);
        EasyMock.expectLastCall();
        EasyMock.replay(mockRestTemplate);

        carService.updateCar(expCar);
    }

    @Test
    public void deleteCarByIdTest(){
        mockRestTemplate.delete("http://localhost:8090/cars/1");
        EasyMock.expectLastCall();
        EasyMock.replay(mockRestTemplate);

        carService.deleteCarById(1);
    }

    @Test
    public void getNumberOfCarsTest() {
        int numOfCar = 3;
        EasyMock.expect(mockRestTemplate
                .getForObject("http://localhost:8090/carsNum", Integer.class))
                .andReturn(numOfCar);
        EasyMock.replay(mockRestTemplate);

        int result = carService.getNumberOfCars();
        Assert.assertEquals(numOfCar, result);
    }

    @Test
    public void getAllCarsDtoWithCrewTest() {
        CarDtoWithCrew car = new CarDtoWithCrew("5555 AA-1", "Ambulance", 2);
        car.setCarId(1);
        List cars = Arrays.asList(car);
        ResponseEntity entity = new ResponseEntity(cars, HttpStatus.FOUND);

        EasyMock.expect(mockRestTemplate.getForEntity("http://localhost:8090/cars", List.class))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Collection<CarDtoWithCrew> results
                = carService.getAllCarsDtoWithCrew();

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }

}
