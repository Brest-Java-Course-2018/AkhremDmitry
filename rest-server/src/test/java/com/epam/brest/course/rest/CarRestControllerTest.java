package com.epam.brest.course.rest;

import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.service.CarService;
import org.easymock.EasyMock;
import org.hamcrest.Matchers;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(carRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        EasyMock.reset(mockCarService);
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

        EasyMock.verify(mockCarService);
    }

}
