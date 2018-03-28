package com.epam.brest.course.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:dao-test.xml"})
@Rollback
@Transactional
public class CarDaoImplTest {

    @Autowired
    CarDao carDao;

    private static final int ID = 1;
    private static final String REGISTRATIONPLATE = "4724 AA-1";
    private static final String DESCRIPTION = "Ambulance transport";


    @Test
    public void getAllCarTest(){
        Collection<Car> cars = carDao.getAllCar();
        Assert.assertFalse(cars.isEmpty());
    }

    @Test
    public void getCarById(){
        Car car = carDao.getCarById(ID);
        Assert.assertEquals(ID, car.getCarId());
        Assert.assertEquals("3347 AO-1", car.getRegistrationPlate());
        Assert.assertEquals("Paramedics", car.getDescription());

    }

    @Test
    public void addCar(){
        Car expCar = new Car(REGISTRATIONPLATE, DESCRIPTION);
        Collection<Car> carsBefore = carDao.getAllCar();
        Car actCar = carDao.addCar(expCar);
        Collection<Car> carsAfter = carDao.getAllCar();

        Assert.assertTrue(carsBefore.size() == carsAfter.size()-1);
        Assert.assertEquals(REGISTRATIONPLATE, actCar.getRegistrationPlate());
        Assert.assertEquals(DESCRIPTION, actCar.getDescription());
    }

    @Test
    public void updateCar(){
        Car expCar = new Car(REGISTRATIONPLATE, DESCRIPTION);

        Car addedCar = carDao.addCar(new Car("car", "some description"));
        expCar.setCarId(addedCar.getCarId());

        Collection<Car> carsBefore = carDao.getAllCar();
        carDao.updateCar(expCar);
        Collection<Car> carsAfter = carDao.getAllCar();

        Car actCar = carDao.getCarById(expCar.getCarId());

        Assert.assertTrue(carsBefore.size() == carsAfter.size());
        Assert.assertEquals(expCar, actCar);
    }

    @Test
    public void deleteCar(){
        Car car = new Car(REGISTRATIONPLATE, DESCRIPTION);
        car = carDao.addCar(car);

        Collection<Car> carsBefore = carDao.getAllCar();
        carDao.deleteCarById(car.getCarId());
        Collection<Car> carsAfter = carDao.getAllCar();

        Assert.assertTrue(carsBefore.size()-1 == carsAfter.size());
    }



}
