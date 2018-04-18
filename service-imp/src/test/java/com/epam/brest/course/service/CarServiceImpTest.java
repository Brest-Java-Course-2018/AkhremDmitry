package com.epam.brest.course.service;

import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CarDtoWithCrew;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:service-test.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class CarServiceImpTest {

    @Autowired
    private CarService carService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static final int ID = 1;
    private static final String REGISTRATIONPLATE = "2345 KK-1";
    private static final String DESCRIPTION = "Ambulance";

    @Test
    public void getAllCarsDto(){
        Collection<CarDto> cars = carService.getAllCarsDto();
        Assert.assertFalse(cars.isEmpty());
    }

    @Test
    public void getCarById() {
        Car car = carService.getCarById(ID);
        Assert.assertEquals(ID, car.getCarId());
        Assert.assertEquals("3347 AO-1", car.getRegistrationPlate());
        Assert.assertEquals("Paramedics", car.getDescription());
    }

    @Test
    public void addCarTest(){
        Car expCar = new Car(REGISTRATIONPLATE, DESCRIPTION);
        int numCarsBefore = carService.getNumberOfCars();
        Car actCar = carService.addCar(expCar);
        int countCarsAfter = carService.getNumberOfCars();

        Assert.assertEquals(numCarsBefore, countCarsAfter-1);
        Assert.assertEquals(REGISTRATIONPLATE, actCar.getRegistrationPlate());
        Assert.assertEquals(DESCRIPTION, actCar.getDescription());
    }

    @Test
    public void addCarTestWithRule(){
        carService.addCar(new Car(REGISTRATIONPLATE, DESCRIPTION));
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Car with the same registration plate already exists in DB.");
        carService.addCar(new Car(REGISTRATIONPLATE, "test"));
    }

    @Test
    public void updateCar(){
        Car expCar = new Car(REGISTRATIONPLATE, DESCRIPTION);

        Car addedCar = carService.addCar(new Car("car", "some description"));
        expCar.setCarId(addedCar.getCarId());

        int numCarsBefore = carService.getNumberOfCars();
        carService.updateCar(expCar);
        int numCarsAfter = carService.getNumberOfCars();

        Car actCar = carService.getCarById(expCar.getCarId());

        Assert.assertTrue(numCarsBefore == numCarsAfter);
        Assert.assertEquals(expCar.getCarId(), actCar.getCarId());
        Assert.assertEquals(expCar.getRegistrationPlate(), actCar.getRegistrationPlate());
        Assert.assertEquals(expCar.getDescription(), actCar.getDescription());
    }

    @Test
    public void deleteCar(){
        Car car = new Car(REGISTRATIONPLATE, DESCRIPTION);
        car = carService.addCar(car);

        int numCarsBefore = carService.getNumberOfCars();
        carService.deleteCarById(car.getCarId());
        int numCarsAfter = carService.getNumberOfCars();

        Assert.assertEquals(numCarsBefore-1, numCarsAfter);
    }

    @Test
    public void getNunberOfCars(){
        int expNumOfCars = carService.getAllCarsDto().size();
        int actNumOfCars = carService.getNumberOfCars();

        Assert.assertTrue(expNumOfCars == actNumOfCars);

        carService.addCar(new Car());
        actNumOfCars = carService.getNumberOfCars();

        Assert.assertEquals(expNumOfCars+1, actNumOfCars);
    }

    @Test
    public void getAllCarDtoWithCrewTest(){
        Collection<CarDtoWithCrew> cars = carService.getAllCarsDtoWithCrew();
        Assert.assertFalse(cars.isEmpty());
    }


}
