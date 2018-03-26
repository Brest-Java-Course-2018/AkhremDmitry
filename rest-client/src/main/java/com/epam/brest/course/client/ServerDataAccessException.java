package com.epam.brest.course.client;

public class ServerDataAccessException extends RuntimeException{

    public ServerDataAccessException(String massage) {
        super(massage);
    }
}
