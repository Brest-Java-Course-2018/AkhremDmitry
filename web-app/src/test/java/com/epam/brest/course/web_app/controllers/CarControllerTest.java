package com.epam.brest.course.web_app.controllers;


import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dto.CarDtoWithCrew;
import com.epam.brest.course.service.CarService;
import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    private static final CarDtoWithCrew CAR_DTO = new CarDtoWithCrew();
    private static final Car CAR = new Car();
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

        CAR_DTO.setCarId(ID);
        CAR_DTO.setRegistrationPlate(REGISTRATIONPLATE);
        CAR_DTO.setDescription(DESCRIPTION);
        CAR_DTO.setNumberOfCrew(NUMBEROFCREW);

        CAR.setCarId(ID);
        CAR.setRegistrationPlate(REGISTRATIONPLATE);
        CAR.setDescription(DESCRIPTION);

        EasyMock.reset(mockCarService);
    }

    @Test
    public void getCarsTest() throws Exception {
        EasyMock.expect(mockCarService.getAllCarsDtoWithCrew())
                .andReturn(Arrays.asList(CAR_DTO));
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
    public void getCarAddTest() throws Exception {

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

    @Test
    public void getCarUpdateTest() throws Exception {
        EasyMock.expect(mockCarService.getCarById(ID)).andReturn(CAR);
        EasyMock.replay(mockCarService);

        mockMvc.perform(
                get("/editCar/{id}", ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("isEdit"))
                .andExpect(model().attributeExists("car"))
                .andExpect(model().attribute("isEdit", true))
                .andExpect(model().attribute("car", CAR))
                .andExpect(view().name("car"));

        EasyMock.verify(mockCarService);
    }

    @Test
    public void deleteCarTest() throws Exception {
        mockCarService.deleteCarById(ID);
        EasyMock.expectLastCall();
        EasyMock.replay(mockCarService);

        mockMvc.perform(
                get("/car/{id}/delete", ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/cars"));

        EasyMock.verify(mockCarService);
    }

    @Test
    public void addCarTest() throws Exception {
        EasyMock.expect(mockCarService.addCar(CAR)).andReturn(CAR);
        EasyMock.replay(mockCarService);

        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/car", CAR))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/cars"));

        EasyMock.verify(mockCarService);
    }

    @Test
    public void updateCarTest() throws Exception {
        mockCarService.updateCar(CAR);
        EasyMock.expectLastCall();
        EasyMock.replay(mockCarService);

        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/editCar/" + CAR.getCarId(), CAR))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/cars"));

        EasyMock.verify(mockCarService);
    }

}
