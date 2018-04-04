package com.epam.brest.course.rest;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.service.CallService;
import org.easymock.EasyMock;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class CallRestControllerTest {

    @Autowired
    private CallRestController callRestController;

    @Autowired
    private CallService mockCallService;

    private MockMvc mockMvc;

    private static final int ID = 1;
    private static final Call CALL = new Call();

    @BeforeClass
    public static void before() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        Date dateCall = new Date(dateFormat.parse("2018-3-14").getTime());
        CALL.setCallId(ID);
        CALL.setDescription("Some description");
        CALL.setDateCall(dateCall);
        CALL.setAddress("Address");
        CALL.setCrewId(1);
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(callRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        EasyMock.reset(mockCallService);
    }

    @Test
    public void getAllCallsTest() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        Date dateCall = new Date(dateFormat.parse("2018-3-14").getTime());

        EasyMock.expect(mockCallService.getAllCall())
                .andReturn(Arrays.asList(CALL));
        EasyMock.replay(mockCallService);

        mockMvc.perform(
                get("/calls")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].callId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].dateCall", Matchers.is(dateCall.getTime())))
                .andExpect(jsonPath("$[0].address", Matchers.is("Address")))
                .andExpect(jsonPath("$[0].description", Matchers.is("Some description")))
                .andExpect(jsonPath("$[0].crewId", Matchers.is(1)));

        EasyMock.verify(mockCallService);
    }

    @Test
    public void getAllCallByDateTest() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        Date dateCall = new Date(dateFormat.parse("2018-3-14").getTime());

        EasyMock.expect(mockCallService.getAllCallByDate(dateCall, dateCall))
                .andReturn(Arrays.asList(CALL));
        EasyMock.replay(mockCallService);

        mockMvc.perform(
                get("/calls/" + dateCall + "/" + dateCall)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].callId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].dateCall", Matchers.is(dateCall.getTime())))
                .andExpect(jsonPath("$[0].address", Matchers.is("Address")))
                .andExpect(jsonPath("$[0].description", Matchers.is("Some description")))
                .andExpect(jsonPath("$[0].crewId", Matchers.is(1)));

        EasyMock.verify(mockCallService);
    }

    @Test
    public void getCallByIdTest() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        Date dateCall = new Date(dateFormat.parse("2018-3-14").getTime());

        EasyMock.expect(mockCallService.getCallById(ID)).andReturn(CALL);
        EasyMock.replay(mockCallService);

        mockMvc.perform(
                get("/calls/" + ID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("callId", Matchers.is(1)))
                .andExpect(jsonPath("dateCall", Matchers.is(dateCall.getTime())))
                .andExpect(jsonPath("address", Matchers.is("Address")))
                .andExpect(jsonPath("description", Matchers.is("Some description")))
                .andExpect(jsonPath("crewId", Matchers.is(1)));

        EasyMock.verify(mockCallService);
    }

    @Test
    public void addCallTest() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        Date dateCall = new Date(dateFormat.parse("2018-3-14").getTime());

        EasyMock.expect(mockCallService.addCall(EasyMock.anyObject())).andReturn(CALL);
        EasyMock.replay(mockCallService);

        mockMvc.perform(
                post("/calls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"callId\":1,\"dateCall\":1520974800000," +
                                "\"description\":\"Some description\"," +
                                "\"address\":\"Address\",\"crewId\":1}")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("callId", Matchers.is(1)))
                .andExpect(jsonPath("dateCall", Matchers.is(dateCall.getTime())))
                .andExpect(jsonPath("address", Matchers.is("Address")))
                .andExpect(jsonPath("description", Matchers.is("Some description")))
                .andExpect(jsonPath("crewId", Matchers.is(1)));

        EasyMock.verify(mockCallService);
    }

    @Test
    public void updateCallTest() throws Exception {
        mockCallService.updateCall(EasyMock.anyObject());
        EasyMock.expectLastCall();

        EasyMock.replay(mockCallService);

        mockMvc.perform(
                put("/calls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"callId\":1,\"dateCall\":1520974800000," +
                                "\"description\":\"Some description\"," +
                                "\"address\":\"Address\",\"crewId\":1}")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());

        EasyMock.verify(mockCallService);
    }

    @Test
    public void deleteCallByIdTest() throws Exception {
        mockCallService.deleteCallById(ID);
        EasyMock.expectLastCall();

        EasyMock.replay(mockCallService);

        mockMvc.perform(
                delete("/calls/{id}", ID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());

        EasyMock.verify(mockCallService);
    }

}