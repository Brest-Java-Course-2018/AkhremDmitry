package com.epam.brest.course.client.rest;

import com.epam.brest.course.client.ServerDataAccessException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class CustomResponseErrorHandler implements ResponseErrorHandler{

    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return errorHandler.hasError(clientHttpResponse);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        throw new ServerDataAccessException("ERROR: "+ clientHttpResponse.getStatusCode()
        + " : " + clientHttpResponse.getStatusText() +" : " + clientHttpResponse.getBody());
    }
}
