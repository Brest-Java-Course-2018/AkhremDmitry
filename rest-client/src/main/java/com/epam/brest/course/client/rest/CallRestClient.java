package com.epam.brest.course.client.rest;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.service.CallService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class CallRestClient implements CallService {

    private String url;

    private RestTemplate restTemplate;

    private static final Logger LOGGER = LogManager.getLogger();

    public CallRestClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public final Call getCallById(final int callId) {
        LOGGER.debug("getCallById({})", callId);
        ResponseEntity<Call> responseEntity =
                restTemplate.getForEntity(url + "/" + callId, Call.class);
        Call call = responseEntity.getBody();
        return call;
    }

    @Override
    public final Call addCall(final Call call) {
        LOGGER.debug("addCall({})", call);
        ResponseEntity<Call> responseEntity =
                restTemplate.postForEntity(url, call, Call.class);
        Call resultCall = responseEntity.getBody();
        return resultCall;
    }

    @Override
    public final void updateCall(final Call call) {
        LOGGER.debug("updateCall({})", call);
        restTemplate.put(url, call);
    }

    @Override
    public final void deleteCallById(final int callId) {
        LOGGER.debug("deleteCallById({})", callId);
        restTemplate.delete(url + "/" + callId);
    }

    @Override
    public final Collection<Call> getAllCall() {
        LOGGER.debug("getAllCall()");
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url, List.class);
        List<Call> calls = (List<Call>) responseEntity.getBody();
        return calls;
    }

    @Override
    public final Collection<Call> getAllCallByDate(final Date startDate,
                                                   final Date endDate) {
        LOGGER.debug("getAllCallByDate({}, {})", startDate, endDate);
        ResponseEntity responseEntity =
                restTemplate
                        .getForEntity(url + "/" + startDate + "/" + endDate,
                                List.class);
        List<Call> calls = (List<Call>) responseEntity.getBody();
        return calls;
    }
}
