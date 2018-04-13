package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home controller.
 */
@Controller
public class HomeController {

    /**
     * Redirect to cars.
     *
     * @return template name
     */
    @GetMapping(value = "/*")
    public final String defaultPageRedirect() {
        return "redirect:/calls";
    }

}