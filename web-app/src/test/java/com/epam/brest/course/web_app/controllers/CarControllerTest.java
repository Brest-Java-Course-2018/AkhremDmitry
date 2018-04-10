package com.epam.brest.course.web_app.controllers;


import com.epam.brest.course.dao.Car;
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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:webappMockTestConfig.xml")
public class CarControllerTest {

    @Autowired
    private CarService mockCarService;

    @Autowired
    private CarController carController;

    private MockMvc mockMvc;

    private static final CarDtoWithCrew CAR = new CarDtoWithCrew();
    private static final int ID = 1;
    private static final String REGISTRATIONPLATE = "8888 AI-1";
    private static final String DESCRIPTION = "Some ambulance car";
    private static final int NUMBEROFCREW = 2;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(carController)
                .setViewResolvers(viewResolver)
                .build();

        CAR.setCarId(ID);
        CAR.setRegistrationPlate(REGISTRATIONPLATE);
        CAR.setDescription(DESCRIPTION);
        CAR.setNumberOfCrew(NUMBEROFCREW);

        EasyMock.reset(mockCarService);
    }

    @Test
    public void carsTest() throws Exception {
        EasyMock.expect(mockCarService.getAllCarsDtoWithCrew())
                .andReturn(Arrays.asList(CAR));
        EasyMock.replay(mockCarService);

        mockMvc.perform(
                get("/cars")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cars"))
                .andExpect(view().name("cars"));

        EasyMock.verify(mockCarService);
    }

    @Test
    public void carTest() throws Exception {

        mockMvc.perform(
                get("/car")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("isEdit"))
                .andExpect(model().attributeExists("car"))
                .andExpect(model().attribute("isEdit", false))
                .andExpect(model().attribute("car", new Car()))
                .andExpect(view().name("car"));
    }

}
