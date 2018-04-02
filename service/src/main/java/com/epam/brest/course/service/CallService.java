package com.epam.brest.course.service;

import com.epam.brest.course.dao.Call;

import java.sql.Date;
import java.util.Collection;

/**
 * Call service interface.
 */
public interface CallService {

    /**
     * Return call by id from database.
     * @param callId id
     * @return Call
     */
    Call getCallById(int callId);

    /**
     * Add call to database.
     * @param call new call
     * @return call with id
     */
    Call addCall(Call call);

    /**
     * Update call in database.
     * @param call call
     */
    void updateCall(Call call);

    /**
     * Remove call by id from database.
     * @param callId call id
     */
    void deleteCallById(int callId);

    /**
     * Return all call from database.
     * @return Collection Call.
     */
    Collection<Call> getAllCall();

    /**
     * Return all call by date.
     * @param startDate date of first call
     * @param endDate date of last call
     * @return Collection Call by date.
     */
    Collection<Call> getAllCallByDate(Date startDate, Date endDate);
}
