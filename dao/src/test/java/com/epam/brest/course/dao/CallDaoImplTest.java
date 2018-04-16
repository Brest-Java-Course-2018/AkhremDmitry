package com.epam.brest.course.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:dao-test.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class CallDaoImplTest {

    @Autowired
    private CallDao callDao;

    @Test
    public void getCallById() {
        Call expCall = new Call();
        expCall.setDateCall(Date.valueOf("2018-3-14"));
        expCall.setDescription("Some description");
        expCall.setAddress("Address");
        expCall.setCrewId(1);

        expCall = callDao.addCall(expCall);

        Call actCall = callDao.getCallById(expCall.getCallId());

        Assert.assertEquals(expCall, actCall);
    }

    @Test
    public void addCall() {
        Call expCall = new Call();
        expCall.setDateCall(Date.valueOf("2018-3-14"));
        expCall.setDescription("Some description");
        expCall.setAddress("Address");
        expCall.setCrewId(1);

        int numCallsBefore = callDao.getAllCall().size();
        Call actCall = callDao.addCall(expCall);
        int numCallsAfter = callDao.getAllCall().size();

        Assert.assertTrue(numCallsBefore == numCallsAfter - 1);
        Assert.assertEquals(expCall.getDateCall(), actCall.getDateCall());
        Assert.assertEquals(expCall.getDescription(), actCall.getDescription());
        Assert.assertEquals(expCall.getAddress(), actCall.getAddress());
        Assert.assertEquals(expCall.getCrewId(), actCall.getCrewId());
    }


    @Test
    public void updateCall() {
        Call expCall = new Call();
        expCall.setDateCall(Date.valueOf("2018-3-14"));
        expCall.setDescription("Some description");
        expCall.setAddress("Address");
        expCall.setCrewId(1);

        Call addedCall = new Call();
        addedCall.setDateCall(Date.valueOf("2010-3-14"));
        addedCall.setDescription("So");
        addedCall.setAddress("A");
        addedCall.setCrewId(2);

        addedCall = callDao.addCall(addedCall);
        expCall.setCallId(addedCall.getCallId());

        int numCallsBefore = callDao.getAllCall().size();
        callDao.updateCall(expCall);
        int numCallsAfter = callDao.getAllCall().size();

        Call actCall = callDao.getCallById(expCall.getCallId());

        Assert.assertTrue(numCallsBefore == numCallsAfter);
        Assert.assertEquals(expCall, actCall);
    }

    @Test
    public void deleteCall() {
        Call call = new Call();
        call.setDateCall(Date.valueOf("2018-3-14"));
        call.setDescription("Some description");
        call.setAddress("Address");
        call.setCrewId(1);

        call = callDao.addCall(call);

        int numCallsBefore = callDao.getAllCall().size();
        callDao.deleteCallById(call.getCallId());
        int numCallsAfter = callDao.getAllCall().size();

        Assert.assertTrue(numCallsBefore - 1 == numCallsAfter);
    }

    @Test
    public void getAllCall() {
        Collection<Call> calls = callDao.getAllCall();
        Assert.assertFalse(calls.isEmpty());
    }


    @Test
    public void getAllCallByDateTest() {
        Date startDate = Date.valueOf("2018-3-23");
        Date endDate = Date.valueOf("2018-3-23");
        ;

        Collection<Call> calls =
                callDao.getAllCallByDate(startDate, endDate);
        Assert.assertFalse(calls.isEmpty());
        Assert.assertTrue(1 == calls.size());
    }
}
