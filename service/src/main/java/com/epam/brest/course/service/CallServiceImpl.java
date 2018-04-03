package com.epam.brest.course.service;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.dao.CallDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.Collection;

/**
 * CallServiceImpl.
 */
public class CallServiceImpl implements CallService {

    /**
     * Property callDao.
     */
    private CallDao callDao;

    /**
     * Logger init.
     */
    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * Setter for callDao.
     * @param callDao DAO of call.
     */
    public void setCallDao(CallDao callDao) {
        this.callDao = callDao;
    }

    @Override
    public Call getCallById(int callId) {
        LOGGER.debug("getCallById()");
        return callDao.getCallById(callId);
    }

    @Override
    public Call addCall(Call call) {
        LOGGER.debug("addCall({})");
        return callDao.addCall(call);
    }

    @Override
    public void updateCall(Call call) {
        LOGGER.debug("updateCall({})", call);
        callDao.updateCall(call);
    }

    @Override
    public void deleteCallById(int callId) {
        LOGGER.debug("deleteCallById({})", callId);
        callDao.deleteCallById(callId);
    }

    @Override
    public Collection<Call> getAllCall() {
        LOGGER.debug("getAllCall()");
        return callDao.getAllCall();
    }

    @Override
    public Collection<Call> getAllCallByDate(Date startDate, Date endDate) {
        LOGGER.debug("getAllCallByDate({}, {})", startDate, endDate);
        return callDao.getAllCallByDate(startDate, endDate);
    }
}
