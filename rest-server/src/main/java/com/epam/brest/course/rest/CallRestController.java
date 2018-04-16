package com.epam.brest.course.rest;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.service.CallService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;

@RestController
public class CallRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CallService callService;

    @GetMapping(value = "/calls")
    public final Collection<Call> getAllCalls() {
        LOGGER.debug("getAllCalls()");
        return callService.getAllCall();
    }

    @GetMapping(value = "/calls/{startDate}/{endDate}")
    public final Collection<Call> getAllCallByDate(
            @PathVariable(value = "startDate") final String startDate,
            @PathVariable(value = "endDate") final String endDate) {
        LOGGER.debug("getAllCallByDate({}, {})", startDate, endDate);
        Collection<Call> calls = callService
                .getAllCallByDate(Date.valueOf(startDate), Date.valueOf(endDate));
        return calls;
    }

    @GetMapping(value = "/calls/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final Call getCallById(@PathVariable final int id) {
        LOGGER.debug("getCallById({})", id);
        Call call = callService.getCallById(id);
        return call;
    }

    @PostMapping(value = "/calls")
    @ResponseStatus(HttpStatus.CREATED)
    public final Call addCall(@RequestBody final Call call) {
        LOGGER.debug("addCall({})", call);
        return callService.addCall(call);
    }

    @PutMapping(value = "/calls")
    public final void updateCall(@RequestBody final Call call) {
        LOGGER.debug("updateCall({})", call);
        callService.updateCall(call);
    }

    @DeleteMapping(value = "/calls/{id}")
    public final void deleteCallById(@PathVariable(value = "id") final int callId) {
        LOGGER.debug("deleteCallById({})", callId);
        callService.deleteCallById(callId);
    }
}
