package com.epam.brest.course.rest;

import com.epam.brest.course.dao.Crew;
import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import com.epam.brest.course.service.CrewService;
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

import java.sql.Date;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class CrewRestControllerTest {

    @Autowired
    CrewRestController crewRestController;

    @Autowired
    CrewService mockCrewService;

    private MockMvc mockMvc;

    private static final int ID = 1;
    private static final String NAME = "Crew1";
    private static final String DESCRIPTION = "Some crew";
    private static final int CARID = 1;
    private static final int NUMBEROFCALLS = 4;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(crewRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        EasyMock.reset(mockCrewService);
    }

    @After
    public void check() {
        EasyMock.verify(mockCrewService);
    }

    @Test
    public void getAllCrewDtoTest() throws Exception {
        CrewDto crew = new CrewDto(NAME);
        crew.setCrewId(ID);
        EasyMock.expect(mockCrewService.getAllCrewDto())
                .andReturn(Arrays.asList(crew));
        EasyMock.replay(mockCrewService);

        mockMvc.perform(
                get("/crewsDto")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].crewId", Matchers.is(ID)))
                .andExpect(jsonPath("$[0].crewName", Matchers.is(NAME)));
    }

    @Test
    public void getCrewByIdTest() throws Exception {
        Crew crew = new Crew(NAME, DESCRIPTION, CARID);
        crew.setCrewId(ID);

        EasyMock.expect(mockCrewService.getCrewById(ID)).andReturn(crew);
        EasyMock.replay(mockCrewService);

        mockMvc.perform(
                get("/crews/{id}", ID).accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("crewId", Matchers.is(ID)))
                .andExpect(jsonPath("crewName", Matchers.is(NAME)))
                .andExpect(jsonPath("description", Matchers.is(DESCRIPTION)))
                .andExpect(jsonPath("carId", Matchers.is(CARID)));
    }

    @Test
    public void addCrewTest() throws Exception {
        Crew crew = new Crew(NAME, DESCRIPTION, CARID);
        EasyMock.expect(mockCrewService.addCrew(crew)).andReturn(crew);
        EasyMock.replay(mockCrewService);

        Gson gson = new Gson();
        mockMvc.perform(
                post("/crews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(crew))
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("crewName", Matchers.is(NAME)))
                .andExpect(jsonPath("description", Matchers.is(DESCRIPTION)))
                .andExpect(jsonPath("carId", Matchers.is(CARID)));
    }

    @Test
    public void updateCrewTest() throws Exception {
        Crew crew = new Crew(NAME, DESCRIPTION, CARID);
        crew.setCrewId(ID);
        mockCrewService.updateCrew(crew);
        EasyMock.expectLastCall();
        EasyMock.replay(mockCrewService);

        Gson gson = new Gson();
        mockMvc.perform(
                put("/crews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(crew))
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCrewByIdTest() throws Exception {
        mockCrewService.deleteCrewById(ID);
        EasyMock.expectLastCall();
        EasyMock.replay(mockCrewService);

        mockMvc.perform(
                delete("/crews/{id}", ID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllCrewDtoWithCallTest() throws Exception {
        CrewDtoWithCall crew = new CrewDtoWithCall(NAME, DESCRIPTION, NUMBEROFCALLS);
        crew.setCrewId(ID);

        EasyMock.expect(mockCrewService.getAllCrewDtoWithCall())
                .andReturn(Arrays.asList(crew));
        EasyMock.replay(mockCrewService);

        mockMvc.perform(
                get("/crews")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].crewId", Matchers.is(ID)))
                .andExpect(jsonPath("$[0].crewName", Matchers.is(NAME)))
                .andExpect(jsonPath("$[0].description", Matchers.is(DESCRIPTION)))
                .andExpect(jsonPath("$[0].numberOfCalls", Matchers.is(NUMBEROFCALLS)));
    }

    @Test
    public void getAllCrewDtoWithCallByDateTest() throws Exception {
        Date startDate = Date.valueOf("2018-3-15");
        Date endDate = Date.valueOf("2018-3-15");

        CrewDtoWithCall crew = new CrewDtoWithCall(NAME, DESCRIPTION, NUMBEROFCALLS);
        crew.setCrewId(ID);

        EasyMock.expect(mockCrewService
                .getAllCrewDtoWithCallByDate(startDate, endDate))
                .andReturn(Arrays.asList(crew));
        EasyMock.replay(mockCrewService);

        mockMvc.perform(
                get("/crews/" + startDate + "/" + endDate)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].crewId", Matchers.is(ID)))
                .andExpect(jsonPath("$[0].crewName", Matchers.is(NAME)))
                .andExpect(jsonPath("$[0].description", Matchers.is(DESCRIPTION)))
                .andExpect(jsonPath("$[0].numberOfCalls", Matchers.is(NUMBEROFCALLS)));
    }

}
