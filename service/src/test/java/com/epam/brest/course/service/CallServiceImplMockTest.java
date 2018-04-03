package com.epam.brest.course.service;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.dao.CallDao;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mocktest.xml"})
public class CallServiceImplMockTest {

    @Autowired
    private CallService callService;

    @Autowired
    private CallDao mockCallDao;

    private static final int ID = 1;

    private static final Call CALL = new Call();

    @BeforeClass
    public static void before() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        Date dateCall = new Date(dateFormat.parse("2018-3-14").getTime());
        CALL.setDateCall(dateCall);
        CALL.setDescription("Some description");
        CALL.setAddress("Address");
        CALL.setCrewId(1);
    }

    @After
    public void after(){
        EasyMock.verify(mockCallDao);
        EasyMock.reset(mockCallDao);
    }

    @Test
    public void getCallByIdTest(){
        EasyMock.expect(mockCallDao.getCallById(ID)).andReturn(CALL);

        EasyMock.replay(mockCallDao);

        callService.getCallById(ID);
    }

    @Test
    public void addCallTest(){
        EasyMock.expect(mockCallDao.addCall(CALL)).andReturn(CALL);

        EasyMock.replay(mockCallDao);

        callService.addCall(CALL);
    }

    @Test
    public void updateCallTest(){
        mockCallDao.updateCall(CALL);
        EasyMock.expectLastCall();

        EasyMock.replay(mockCallDao);

        callService.updateCall(CALL);
    }

    @Test
    public void deleteCallById(){
        mockCallDao.deleteCallById(ID);
        EasyMock.expectLastCall();

        EasyMock.replay(mockCallDao);

        callService.deleteCallById(ID);
    }

    @Test
    public void getAllCall(){
        Collection<Call> calls = Arrays.asList(CALL);
        EasyMock.expect(mockCallDao.getAllCall()).andReturn(calls);

        EasyMock.replay(mockCallDao);

        callService.getAllCall();
    }

    @Test
    public void getAllCallByDate() throws ParseException {
        Collection<Call> calls = Arrays.asList(CALL);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        Date startDate = new Date(dateFormat.parse("2018-3-23").getTime());
        Date endDate = new Date(dateFormat.parse("2018-3-23").getTime());

        EasyMock.expect(mockCallDao
                .getAllCallByDate(startDate, endDate))
                .andReturn(calls);

        EasyMock.replay(mockCallDao);

        callService.getAllCallByDate(startDate, endDate);
    }
}
