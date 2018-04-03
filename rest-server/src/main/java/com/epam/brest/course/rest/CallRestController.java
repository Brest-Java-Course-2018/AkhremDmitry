package com.epam.brest.course.rest;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.service.CallService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CallRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CallService callService;

    @GetMapping(value = "/calls")
    Collection<Call> getAllCalls(){
        LOGGER.debug("getAllCalls()");
        return callService.getAllCall();
    }
}
