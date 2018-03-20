package com.epam.brest.course.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * VersionController.
 */
@RestController
public class VersionController {
    /**
     * Property VERSION.
     */
    public static final String VERSION = "1.0";

    /**
     * Get version.
     * @return version
     */
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public final String getVersion() {
        return VERSION;
    }

}
