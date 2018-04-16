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
    public static void before() {
        CALL.setDateCall(Date.valueOf("2018-3-14"));
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
    public void getAllCallByDate() {
        Collection<Call> calls = Arrays.asList(CALL);
        Date startDate = Date.valueOf("2018-3-23");
        Date endDate = Date.valueOf("2018-3-23");

        EasyMock.expect(mockCallDao
                .getAllCallByDate(startDate, endDate))
                .andReturn(calls);

        EasyMock.replay(mockCallDao);

        callService.getAllCallByDate(startDate, endDate);
    }
}
