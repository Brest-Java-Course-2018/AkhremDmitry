package com.epam.brest.course.service;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.dao.CallDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collection;

/**
 * CallServiceImpl.
 */
@Service
public class CallServiceImpl implements CallService {

    /**
     * Property callDao.
     */
    @Autowired
    private CallDao callDao;

    /**
     * Logger init.
     */
    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * Setter for callDao.
     *
     * @param callDao DAO of call.
     */
    public final void setCallDao(final CallDao callDao) {
        this.callDao = callDao;
    }

    @Override
    public final Call getCallById(final int callId) {
        LOGGER.debug("getCallById()");
        return callDao.getCallById(callId);
    }

    @Override
    public final Call addCall(final Call call) {
        LOGGER.debug("addCall({})");
        return callDao.addCall(call);
    }

    @Override
    public final void updateCall(final Call call) {
        LOGGER.debug("updateCall({})", call);
        callDao.updateCall(call);
    }

    @Override
    public final void deleteCallById(final int callId) {
        LOGGER.debug("deleteCallById({})", callId);
        callDao.deleteCallById(callId);
    }

    @Override
    public final Collection<Call> getAllCall() {
        LOGGER.debug("getAllCall()");
        return callDao.getAllCall();
    }

    @Override
    public final Collection<Call> getAllCallByDate(
            final Date startDate, final Date endDate) {
        LOGGER.debug("getAllCallByDate({}, {})", startDate, endDate);
        return callDao.getAllCallByDate(startDate, endDate);
    }
}
