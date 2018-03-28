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

    @Test
    public void getAllCarTest(){
        Collection<Car> cars = carDao.getAllCar();
        Assert.assertFalse(cars.isEmpty());
    }


}
