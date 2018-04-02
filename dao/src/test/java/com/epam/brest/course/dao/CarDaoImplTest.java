package com.epam.brest.course.dao;

import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CarDtoWithCrew;
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
        "classpath:dao-test.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class CarDaoImplTest {

    @Autowired
    private CarDao carDao;

    private static final int ID = 1;
    private static final String REGISTRATIONPLATE = "4724 AA-1";
    private static final String DESCRIPTION = "Ambulance transport";


    @Test
    public void getAllCarTest(){
        Collection<CarDto> cars = carDao.getAllCarsDto();
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
        int numCarsBefore = carDao.getNumberOfCars();
        Car actCar = carDao.addCar(expCar);
        int numCarsAfter = carDao.getNumberOfCars();

        Assert.assertTrue(numCarsBefore == numCarsAfter-1);
        Assert.assertEquals(REGISTRATIONPLATE, actCar.getRegistrationPlate());
        Assert.assertEquals(DESCRIPTION, actCar.getDescription());
    }

    @Test
    public void updateCar(){
        Car expCar = new Car(REGISTRATIONPLATE, DESCRIPTION);

        Car addedCar = carDao.addCar(new Car("car", "some description"));
        expCar.setCarId(addedCar.getCarId());

        int numCarsBefore = carDao.getNumberOfCars();
        carDao.updateCar(expCar);
        int numCarsAfter = carDao.getNumberOfCars();

        Car actCar = carDao.getCarById(expCar.getCarId());

        Assert.assertTrue(numCarsBefore == numCarsAfter);
        Assert.assertEquals(expCar, actCar);
    }

    @Test
    public void deleteCar(){
        Car car = new Car(REGISTRATIONPLATE, DESCRIPTION);
        car = carDao.addCar(car);

        int numCarsBefore = carDao.getNumberOfCars();
        carDao.deleteCarById(car.getCarId());
        int numCarsAfter = carDao.getNumberOfCars();

        Assert.assertTrue(numCarsBefore-1 == numCarsAfter);
    }

    @Test
    public void getNunberOfCars(){
        int expNumOfCars = carDao.getAllCarsDto().size();
        int actNumOfCars = carDao.getNumberOfCars();

        Assert.assertEquals(expNumOfCars, actNumOfCars);

        carDao.addCar(new Car());
        actNumOfCars = carDao.getNumberOfCars();

        Assert.assertEquals(expNumOfCars+1, actNumOfCars);
    }

    @Test
    public void getAllCarDtoWithCrewTest(){
        Collection<CarDtoWithCrew> cars = carDao.getAllCarsDtoWithCrew();
        Assert.assertFalse(cars.isEmpty());
    }

    @Test
    public void checkCar(){
        carDao.addCar(new Car(REGISTRATIONPLATE, DESCRIPTION));

        int actNumber = carDao.checkCar(REGISTRATIONPLATE);

        Assert.assertEquals(1, actNumber);
    }


}
