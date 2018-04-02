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
}
