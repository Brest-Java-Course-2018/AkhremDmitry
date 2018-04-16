package com.epam.brest.course.web_app.rest;


import com.epam.brest.course.dao.Call;
import com.epam.brest.course.service.CallService;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rest-context.xml")
public class CallRestClientTest {

    @Autowired
    @Qualifier("callRest")
    private CallService callService;

    @Autowired
    private RestTemplate mockRestTemplate;

    private Call expCall = new Call();
    private Date startDate;
    private Date endDate;

    @After
    public void after() {
        EasyMock.verify(mockRestTemplate);
        EasyMock.reset(mockRestTemplate);
    }

    @Before
    public void setUp() {
        startDate = Date.valueOf("2018-3-14");
        endDate = Date.valueOf("2018-3-15");
        expCall.setCallId(1);
        expCall.setAddress("Address");
        expCall.setDescription("Description");
        expCall.setDateCall(startDate);
    }

    @Test
    public void getCallByIdTest() {
        ResponseEntity entity = new ResponseEntity(expCall, HttpStatus.FOUND);

        EasyMock.expect(mockRestTemplate.getForEntity("http://localhost:8090/calls/1", Call.class))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Call actCall = callService.getCallById(1);

        Assert.assertEquals(expCall, actCall);
    }

    @Test
    public void addCallTest() {
        ResponseEntity entity = new ResponseEntity(expCall, HttpStatus.FOUND);

        EasyMock.expect(mockRestTemplate
                .postForEntity("http://localhost:8090/calls", expCall, Call.class))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Call actCall = callService.addCall(expCall);
        Assert.assertEquals(expCall, actCall);
    }

    @Test
    public void updateCallTest() {
        mockRestTemplate.put("http://localhost:8090/calls", expCall);
        EasyMock.expectLastCall();
        EasyMock.replay(mockRestTemplate);

        callService.updateCall(expCall);
    }

    @Test
    public void deleteCallByIdTest() {
        mockRestTemplate.delete("http://localhost:8090/calls/1");
        EasyMock.expectLastCall();
        EasyMock.replay(mockRestTemplate);

        callService.deleteCallById(1);
    }

    @Test
    public void getAllCallTest() {
        List calls = Arrays.asList(expCall);
        ResponseEntity entity = new ResponseEntity(calls, HttpStatus.FOUND);

        EasyMock.expect(mockRestTemplate
                .getForEntity("http://localhost:8090/calls", List.class))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        List<Call> result = (List<Call>) callService.getAllCall();
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void getAllCallByDateTest() {
        List calls = Arrays.asList(expCall);
        ResponseEntity entity = new ResponseEntity(calls, HttpStatus.FOUND);

        EasyMock.expect(mockRestTemplate
                .getForEntity("http://localhost:8090/calls/2018-03-14/2018-03-15", List.class))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        List<Call> result = (List<Call>) callService.getAllCallByDate(startDate, endDate);
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
    }

}
