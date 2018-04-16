package com.epam.brest.course.web_app.rest;

import com.epam.brest.course.dao.Crew;
import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import com.epam.brest.course.service.CrewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Service
public class CrewRestClient implements CrewService {

    @Value("${crew.RestClientUrl}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public final Collection<CrewDto> getAllCrewDto() {
        LOGGER.debug("getAllCrewDto()");
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url + "Dto", List.class);
        List<CrewDto> crews = (List<CrewDto>) responseEntity.getBody();
        return crews;
    }

    @Override
    public final Crew getCrewById(final int crewId) {
        LOGGER.debug("getCrewById({})", crewId);
        ResponseEntity<Crew> responseEntity =
                restTemplate.getForEntity(url + "/" + crewId, Crew.class);
        Crew crew = responseEntity.getBody();
        return crew;
    }

    @Override
    public final Crew addCrew(final Crew crew) {
        LOGGER.debug("addCrew({})", crew);
        ResponseEntity<Crew> responseEntity =
                restTemplate.postForEntity(url, crew, Crew.class);
        Crew resultCrew = responseEntity.getBody();
        return resultCrew;
    }

    @Override
    public final void updateCrew(final Crew crew) {
        LOGGER.debug("updateCrew({})", crew);
        restTemplate.put(url, crew);
    }

    @Override
    public final void deleteCrewById(final int crewId) {
        LOGGER.debug("deleteCrewById({})", crewId);
        restTemplate.delete(url + "/" + crewId);
    }

    @Override
    public final Collection<CrewDtoWithCall> getAllCrewDtoWithCall() {
        LOGGER.debug("getAllCrewDtoWithCall()");
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url, List.class);
        List<CrewDtoWithCall> crews = (List<CrewDtoWithCall>) responseEntity.getBody();
        return crews;
    }

    @Override
    public final Collection<CrewDtoWithCall> getAllCrewDtoWithCallByDate(
            final Date startDate, final Date endDate) {
        LOGGER.debug("getAllCrewDtoWithCallByDate({}, {})", startDate, endDate);
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url + "/" + startDate + "/" + endDate, List.class);
        List<CrewDtoWithCall> crews = (List<CrewDtoWithCall>) responseEntity.getBody();
        return crews;
    }
}
