package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Crew;
import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import com.epam.brest.course.service.CarService;
import com.epam.brest.course.service.CrewService;
import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.BeforeClass;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:webappMockTestConfig.xml")
public class CrewControllerTest {

    @Autowired
    private CarService mockCarService;

    @Autowired
    private CrewService mockCrewService;

    @Autowired
    private CrewController crewController;

    private MockMvc mockMvc;

    private static final int ID = 1;
    private static final int CAR_ID = 4;
    private static final String REGISTRATION_PLATE = "8888 AA-2";
    private static final String NAME = "Crew1";
    private static final String DESCRIPTION = "Some crew1";
    private static final int NUMBEROFCALLS = 2;
    private static final CrewDtoWithCall CREW_DTO = new CrewDtoWithCall();
    private static final CarDto CAR_DTO = new CarDto();
    private static final Crew CREW = new Crew(NAME, DESCRIPTION, CAR_ID);


    @BeforeClass
    public static void before() {
        CREW_DTO.setCrewId(ID);
        CREW_DTO.setCrewName(NAME);
        CREW_DTO.setDescription(DESCRIPTION);
        CREW_DTO.setNumberOfCalls(NUMBEROFCALLS);

        CAR_DTO.setCarId(CAR_ID);
        CAR_DTO.setRegistrationPlate(REGISTRATION_PLATE);

        CREW.setCrewId(ID);
    }

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(crewController)
                .setViewResolvers(viewResolver)
                .build();

        EasyMock.reset(mockCrewService);
    }

    @Test
    public void getCrewsTest() throws Exception {
        EasyMock.expect(mockCrewService.getAllCrewDtoWithCall())
                .andReturn(Arrays.asList(CREW_DTO));
        EasyMock.replay(mockCrewService);

        mockMvc.perform(
                get("/crews")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("crews"))
                .andExpect(view().name("crews"));

        EasyMock.verify(mockCrewService);
    }

    @Test
    public void getAddCrewTest() throws Exception {
        EasyMock.reset(mockCarService);

        EasyMock.expect(mockCarService.getAllCarsDto())
                .andReturn(Arrays.asList(CAR_DTO));
        EasyMock.replay(mockCarService);

        mockMvc.perform(
                get("/crew")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("isEdit"))
                .andExpect(model().attributeExists("crew"))
                .andExpect(model().attributeExists("cars"))
                .andExpect(model().attribute("isEdit", false))
                .andExpect(model().attribute("crew", new Crew()))
                .andExpect(view().name("crew"));

        EasyMock.verify(mockCarService);
    }

    @Test
    public void addCrewTest() throws Exception {
        EasyMock.expect(mockCrewService.addCrew(CREW)).andReturn(CREW);
        EasyMock.replay(mockCrewService);

        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/crew", CREW))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/crews"));

        EasyMock.verify(mockCrewService);
    }

    @Test
    public void getCrewUpdateTest() throws Exception {
        EasyMock.reset(mockCarService);

        EasyMock.expect(mockCarService.getAllCarsDto())
                .andReturn(Arrays.asList(CAR_DTO));
        EasyMock.expect(mockCrewService.getCrewById(ID)).andReturn(CREW);

        EasyMock.replay(mockCarService);
        EasyMock.replay(mockCrewService);

        mockMvc.perform(
                get("/editCrew/{id}", ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("isEdit"))
                .andExpect(model().attributeExists("crew"))
                .andExpect(model().attributeExists("cars"))
                .andExpect(model().attribute("isEdit", true))
                .andExpect(model().attribute("crew", CREW))
                .andExpect(view().name("crew"));

        EasyMock.verify(mockCarService);
        EasyMock.verify(mockCrewService);
    }

    @Test
    public void updateCrewTest() throws Exception {
        mockCrewService.updateCrew(CREW);
        EasyMock.expectLastCall();
        EasyMock.replay(mockCrewService);

        mockMvc.perform(MockMvcRequestBuilderUtils
                .postForm("/editCrew/" + CREW.getCrewId(), CREW))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/crews"));

        EasyMock.verify(mockCrewService);
    }

    @Test
    public void deleteCrewTest() throws Exception {
        mockCrewService.deleteCrewById(ID);
        EasyMock.expectLastCall();
        EasyMock.replay(mockCrewService);

        mockMvc.perform(
                get("/crew/{id}/delete", ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/crews"));

        EasyMock.verify(mockCrewService);
    }
}
