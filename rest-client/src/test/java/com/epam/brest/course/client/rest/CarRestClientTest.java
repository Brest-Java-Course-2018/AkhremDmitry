package com.epam.brest.course.client.rest;


import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.service.CarService;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
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

    @After
    public void after() {
        EasyMock.verify(mockRestTemplate);
        EasyMock.reset(mockRestTemplate);
    }
    @Test
    public void getAllCarsDto() {
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

}
