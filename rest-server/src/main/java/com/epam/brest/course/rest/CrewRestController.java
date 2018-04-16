package com.epam.brest.course.rest;

import com.epam.brest.course.dao.Crew;
import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import com.epam.brest.course.service.CrewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;

@RestController
public class CrewRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CrewService crewService;

    @GetMapping(value = "/crewsDto")
    public final Collection<CrewDto> getAllCrewDto() {
        LOGGER.debug("getAllCrewDto()");
        return crewService.getAllCrewDto();
    }

    @GetMapping(value = "/crews/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final Crew getCrewById(@PathVariable final int id) {
        LOGGER.debug("getCrewById({})", id);
        return crewService.getCrewById(id);
    }

    @PostMapping(value = "/crews")
    @ResponseStatus(HttpStatus.CREATED)
    public final Crew addCrew(@RequestBody final Crew crew) {
        LOGGER.debug("addCrew({})", crew);
        return crewService.addCrew(crew);
    }

    @PutMapping(value = "/crews")
    public final void updateCrew(@RequestBody final Crew crew) {
        LOGGER.debug("updateCrew({})", crew);
        crewService.updateCrew(crew);
    }

    @DeleteMapping(value = "/crews/{id}")
    public final void deleteCrewById(
            @PathVariable(value = "id") final int crewId) {
        LOGGER.debug("deleteCrewById({})", crewId);
        crewService.deleteCrewById(crewId);
    }

    @GetMapping(value = "/crews")
    public final Collection<CrewDtoWithCall> getAllCrewDtoWithCall() {
        LOGGER.debug("getAllCrewDtoWithCall()");
        return crewService.getAllCrewDtoWithCall();
    }

    @GetMapping(value = "/crews/{startDate}/{endDate}")
    public final Collection<CrewDtoWithCall> getAllCrewDtoWithCallByDate(
            @PathVariable(value = "startDate") final String startDate,
            @PathVariable(value = "endDate") final String endDate) {
        LOGGER.debug("getAllCrewDtoWithCallByDate({}, {})",
                startDate, endDate);
        Collection<CrewDtoWithCall> crews =
                crewService.getAllCrewDtoWithCallByDate(Date.valueOf(startDate),
                        Date.valueOf(endDate));
        return crews;
    }

}
