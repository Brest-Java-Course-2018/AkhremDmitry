package com.epam.brest.course.web_app.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController extends RuntimeException{
    /**
     * Exception handler.
     * @param ex Exception
     * @param model Model
     * @return template name.
     */
    @ExceptionHandler(Exception.class)
    public final String exceptionHandler(final Exception  ex,
                                         final Model model) {
        model.addAttribute("Text", ex.getMessage().toString());
        return "error";
    }
}
