package com.epam.brest.course.web_app.rest;


import com.epam.brest.course.dao.Crew;
import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import com.epam.brest.course.service.CrewService;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rest-context.xml")
public class CrewRestClientTest {

    @Autowired
    private CrewService crewService;

    @Autowired
    private RestTemplate mockRestTemplate;

    private Crew expCrew = new Crew();

    @Before
    public void setUp(){
        expCrew.setCrewId(1);
        expCrew.setCrewName("Crew1");
        expCrew.setDescription("Ambulance");
        expCrew.setCarId(2);
    }

    @After
    public void after() {
        EasyMock.verify(mockRestTemplate);
        EasyMock.reset(mockRestTemplate);
    }

    @Test
    public void getAllCrewDtoTest() {
        CrewDto crewDto = new CrewDto("Crew1");
        crewDto.setCrewId(1);
        List crews = Arrays.asList(crewDto);
        ResponseEntity entity = new ResponseEntity(crews, HttpStatus.FOUND);

        EasyMock.expect(mockRestTemplate.getForEntity("http://localhost:8090/crewsDto", List.class))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Collection<CrewDto> results
                = crewService.getAllCrewDto();

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void getCrewByIdTest(){
        ResponseEntity entity = new ResponseEntity(expCrew, HttpStatus.FOUND);

        EasyMock.expect(mockRestTemplate.getForEntity("http://localhost:8090/crews/1", Crew.class))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Crew actCrew = crewService.getCrewById(1);

        Assert.assertEquals(expCrew, actCrew);
    }

    @Test
    public void addCrewTest(){
        ResponseEntity entity = new ResponseEntity(expCrew, HttpStatus.FOUND);

        EasyMock.expect(mockRestTemplate
                .postForEntity("http://localhost:8090/crews", expCrew, Crew.class))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Crew actCrew = crewService.addCrew(expCrew);

        Assert.assertEquals(expCrew, actCrew);
    }

    @Test
    public void updateCrewTest(){
        mockRestTemplate.put("http://localhost:8090/crews", expCrew);
        EasyMock.expectLastCall();
        EasyMock.replay(mockRestTemplate);

        crewService.updateCrew(expCrew);
    }

    @Test
    public void deleteCrewByIdTest(){
        mockRestTemplate.delete("http://localhost:8090/crews/1");
        EasyMock.expectLastCall();
        EasyMock.replay(mockRestTemplate);

        crewService.deleteCrewById(1);
    }

    @Test
    public void getAllCrewDtoWithCallTest() {
        CrewDtoWithCall crew = new CrewDtoWithCall("Crew1", "Ambulance",2);
        List crews = Arrays.asList(crew);
        ResponseEntity entity = new ResponseEntity(crews, HttpStatus.FOUND);

        EasyMock.expect(mockRestTemplate.getForEntity("http://localhost:8090/crews", List.class))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Collection<CrewDtoWithCall> results
                = crewService.getAllCrewDtoWithCall();

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void getAllCrewDtoWithCallByDateTest() {
        Date startDate = Date.valueOf("2018-3-14");
        Date endDate = Date.valueOf("2018-3-15");

        CrewDtoWithCall crew = new CrewDtoWithCall("Crew1", "Ambulance",2);
        List crews = Arrays.asList(crew);
        ResponseEntity entity = new ResponseEntity(crews, HttpStatus.FOUND);

        EasyMock.expect(mockRestTemplate
                .getForEntity("http://localhost:8090/crews/2018-03-14/2018-03-15",
                        List.class))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Collection<CrewDtoWithCall> results
                = crewService.getAllCrewDtoWithCallByDate(startDate, endDate);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }

}
