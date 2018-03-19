package com.epam.brest.course.web_app.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController extends RuntimeException {

    /**
     * Exception handler.
     * @param ex Exception
     * @param model Model
     * @return template name.
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler( Exception  ex, Model model) {
        model.addAttribute  ("Text",ex.getMessage().toString());
        return "error";
    }


//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFound.class)
//    public String ErrorPage(Model model) {
//
//        String errorMsg = "Http Error Code: 404. Resource not found";
//        model.addAttribute  ("Title","404");
//        model.addAttribute  ("Text",errorMsg);
//
//        return "error";
//    }

}


