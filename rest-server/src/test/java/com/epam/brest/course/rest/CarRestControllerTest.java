package com.epam.brest.course.rest;

import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CarDtoWithCrew;
import com.epam.brest.course.service.CarService;
import com.google.gson.Gson;
import org.easymock.EasyMock;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class CarRestControllerTest {

    @Autowired
    private CarRestController carRestController;

    @Autowired
    private CarService mockCarService;

    private MockMvc mockMvc;

    private static final int ID = 1;
    private static final String REGISTRATIONPLATE = "8888 AI-1";
    private static final String DESCRIPTION = "Some ambulance car";
    private static final int NUMBEROFCREW = 2;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(carRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        EasyMock.reset(mockCarService);
    }

    @After
    public void check() {
        EasyMock.verify(mockCarService);
    }

    @Test
    public void getAllCarsDtoTest() throws Exception {
        CarDto carDto = new CarDto(REGISTRATIONPLATE);
        carDto.setCarId(ID);

        EasyMock.expect(mockCarService.getAllCarsDto()).andReturn(Arrays.asList(carDto));
        EasyMock.replay(mockCarService);

        mockMvc.perform(
                get("/carsDto")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].carId", Matchers.is(ID)))
                .andExpect(jsonPath("$[0].registrationPlate", Matchers.is(REGISTRATIONPLATE)));
    }

    @Test
    public void getAllCarsDtoWithCrewTest() throws Exception {
        CarDtoWithCrew car = new CarDtoWithCrew(REGISTRATIONPLATE, DESCRIPTION, NUMBEROFCREW);
        car.setCarId(ID);

        EasyMock.expect(mockCarService.getAllCarsDtoWithCrew()).andReturn(Arrays.asList(car));
        EasyMock.replay(mockCarService);

        mockMvc.perform(
                get("/cars")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].carId", Matchers.is(ID)))
                .andExpect(jsonPath("$[0].registrationPlate", Matchers.is(REGISTRATIONPLATE)))
                .andExpect(jsonPath("$[0].description", Matchers.is(DESCRIPTION)))
                .andExpect(jsonPath("$[0].numberOfCrew", Matchers.is(NUMBEROFCREW)));
    }

    @Test
    public void getCarByIdTest() throws Exception {
        Car car = new Car(REGISTRATIONPLATE, DESCRIPTION);
        car.setCarId(ID);
        EasyMock.expect(mockCarService.getCarById(ID)).andReturn(car);
        EasyMock.replay(mockCarService);

        mockMvc.perform(
                get("/cars/{id}", ID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("carId", Matchers.is(ID)))
                .andExpect(jsonPath("registrationPlate", Matchers.is(REGISTRATIONPLATE)))
                .andExpect(jsonPath("description", Matchers.is(DESCRIPTION)));
    }

    @Test
    public void addCarTest() throws Exception {
        Car car = new Car(REGISTRATIONPLATE, DESCRIPTION);
        EasyMock.expect(mockCarService.addCar(car)).andReturn(car);
        EasyMock.replay(mockCarService);

        Gson gson = new Gson();
        mockMvc.perform(
                post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(car))
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("registrationPlate", Matchers.is(REGISTRATIONPLATE)))
                .andExpect(jsonPath("description", Matchers.is(DESCRIPTION)));
    }

    @Test
    public void updateCarTest() throws Exception {
        Car car = new Car(REGISTRATIONPLATE, DESCRIPTION);
        car.setCarId(ID);
        mockCarService.updateCar(car);
        EasyMock.expectLastCall();
        EasyMock.replay(mockCarService);

        Gson gson = new Gson();
        mockMvc.perform(
                put("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(car))
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCarByIdTest() throws Exception {
        mockCarService.deleteCarById(ID);
        EasyMock.expectLastCall();
        EasyMock.replay(mockCarService);

        mockMvc.perform(
                delete("/cars/{id}", ID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getNumberOfCarsTest() throws Exception {
        EasyMock.expect(mockCarService.getNumberOfCars()).andReturn(2);
        EasyMock.replay(mockCarService);

        mockMvc.perform(
                get("/carsNum")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$", Matchers.is(2)));
    }

}
