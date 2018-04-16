package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.service.CallService;
import com.epam.brest.course.service.CrewService;
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

import java.sql.Date;
import java.text.ParseException;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:webappMockTestConfig.xml")
public class CallControllerTest {

    @Autowired
    private CallController callController;

    @Autowired
    private CrewService mockCrewService;

    @Autowired
    private CallService mockCallService;

    private MockMvc mockMvc;

    private static final int ID = 1;
    private static final Call CALL = new Call();
    private static final CrewDto CREW_DTO = new CrewDto();

    @Before
    public void setUp() throws ParseException {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".html");
        viewResolver.setPrefix("/WEB-INF/templates/");

        mockMvc = MockMvcBuilders.standaloneSetup(callController)
                .setViewResolvers(viewResolver)
                .build();

        CALL.setCallId(ID);
        CALL.setDateCall(Date.valueOf("2018-3-14"));
        CALL.setAddress("Lenina 80");
        CALL.setDescription("heard ache");
        CALL.setCrewId(2);

        CREW_DTO.setCrewId(1);
        CREW_DTO.setCrewName("Crew1");

        EasyMock.reset(mockCallService);
    }

    @Test
    public void getCallsTest() throws Exception {
        EasyMock.expect(mockCallService.getAllCall())
                .andReturn(Arrays.asList(CALL));
        EasyMock.replay(mockCallService);

        mockMvc.perform(
                get("/calls")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("calls"))
                .andExpect(view().name("calls"));

        EasyMock.verify(mockCallService);
    }

    @Test
    public void getAddCallTest() throws Exception {
        EasyMock.reset(mockCrewService);

        EasyMock.expect(mockCrewService.getAllCrewDto())
                .andReturn(Arrays.asList(CREW_DTO));
        EasyMock.replay(mockCrewService);

        mockMvc.perform(
                get("/call")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("isEdit"))
                .andExpect(model().attributeExists("crews"))
                .andExpect(model().attributeExists("call"))
                .andExpect(model().attribute("isEdit", false))
                .andExpect(model().attribute("call", new Call()))
                .andExpect(view().name("call"));

        EasyMock.verify(mockCrewService);
    }

    @Test
    public void addCallTest() throws Exception {
        EasyMock.expect(mockCallService.addCall(CALL)).andReturn(CALL);
        EasyMock.replay(mockCallService);

        mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/call", CALL))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/calls"));

        EasyMock.verify(mockCallService);
    }

    @Test
    public void getUpdateCallTest() throws Exception {
        EasyMock.reset(mockCrewService);

        EasyMock.expect(mockCallService.getCallById(ID))
                .andReturn(CALL);
        EasyMock.expect(mockCrewService.getAllCrewDto())
                .andReturn(Arrays.asList(CREW_DTO));

        EasyMock.replay(mockCallService);
        EasyMock.replay(mockCrewService);

        mockMvc.perform(
                get("/editCall/{id}", ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("isEdit"))
                .andExpect(model().attributeExists("crews"))
                .andExpect(model().attributeExists("call"))
                .andExpect(model().attribute("isEdit", true))
                .andExpect(model().attribute("call", CALL))
                .andExpect(view().name("call"));

        EasyMock.verify(mockCallService);
        EasyMock.verify(mockCrewService);
    }

    @Test
    public void updateCallTest() throws Exception {
        mockCallService.updateCall(CALL);
        EasyMock.expectLastCall();
        EasyMock.replay(mockCallService);

        mockMvc.perform(MockMvcRequestBuilderUtils
                .postForm("/editCall/" + CALL.getCallId(), CALL))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/calls"));

        EasyMock.verify(mockCallService);
    }

    @Test
    public void deleteCrewTest() throws Exception {
        mockCallService.deleteCallById(ID);
        EasyMock.expectLastCall();
        EasyMock.replay(mockCallService);

        mockMvc.perform(
                get("/call/{id}/delete", ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/calls"));

        EasyMock.verify(mockCallService);
    }
}
