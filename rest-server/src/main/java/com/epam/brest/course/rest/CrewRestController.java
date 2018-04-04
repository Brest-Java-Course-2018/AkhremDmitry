package com.epam.brest.course.rest;

import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.service.CrewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CrewRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CrewService crewService;

    public void setCrewService(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping(value = "/crews")
    public Collection<CrewDto> getAllCrewDto() {
        LOGGER.debug("getAllCrewDto()");
        return crewService.getAllCrewDto();
    }
}
