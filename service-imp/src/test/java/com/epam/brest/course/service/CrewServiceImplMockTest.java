package com.epam.brest.course.service;

import com.epam.brest.course.dao.Crew;
import com.epam.brest.course.dao.CrewDao;
import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mocktest.xml"})
public class CrewServiceImplMockTest {

    @Autowired
    private CrewService crewService;

    @Autowired
    private CrewDao mockCrewDao;

    private static final int ID = 1;
    private static final String CREWNAME = "Crew5";
    private static final String DESCRIPTION = "Some description for crew5";
    private static final int CARID = 1;
    private static final int NUMBEROFCALLS = 2;
    private static final Crew CREW = new Crew(CREWNAME, DESCRIPTION, CARID);

    @After
    public void after(){
        EasyMock.verify(mockCrewDao);
        EasyMock.reset(mockCrewDao);
    }

    @Test
    public void getAllCrewDtoTest() {
        Collection<CrewDto> crews = Arrays.asList(new CrewDto(CREWNAME));
        EasyMock.expect(mockCrewDao.getAllCrewDto()).andReturn(crews);

        EasyMock.replay(mockCrewDao);

        crewService.getAllCrewDto();
    }

    @Test
    public void getCrewByIdTest(){
        EasyMock.expect(mockCrewDao.getCrewById(ID)).andReturn(CREW);

        EasyMock.replay(mockCrewDao);

        crewService.getCrewById(ID);
    }

    @Test
    public void addCrewTest(){
        EasyMock.expect(mockCrewDao.addCrew(CREW)).andReturn(CREW);

        EasyMock.replay(mockCrewDao);

        crewService.addCrew(CREW);
    }

    @Test
    public void updateCrewTest(){
        mockCrewDao.updateCrew(CREW);
        EasyMock.expectLastCall();

        EasyMock.replay(mockCrewDao);

        crewService.updateCrew(CREW);
    }

    @Test
    public void deleteCrewByIdTest(){
        mockCrewDao.deleteCrewById(ID);
        EasyMock.expectLastCall();

        EasyMock.replay(mockCrewDao);

        crewService.deleteCrewById(ID);
    }

    @Test
    public void getAllCrewDtoWithCallTest() {
        Collection<CrewDtoWithCall> crews =
                Arrays.asList(new CrewDtoWithCall(CREWNAME, DESCRIPTION, NUMBEROFCALLS));
        EasyMock.expect(mockCrewDao.getAllCrewDtoWithCall()).andReturn(crews);

        EasyMock.replay(mockCrewDao);

        crewService.getAllCrewDtoWithCall();
    }

    @Test
    public void getAllCrewDtoWithCallByDateTest() {
        Date startDate = Date.valueOf("2018-3-23");
        Date endDate = Date.valueOf("2018-3-23");
        Collection<CrewDtoWithCall> crews =
                Arrays.asList(new CrewDtoWithCall(CREWNAME, DESCRIPTION, NUMBEROFCALLS));

        EasyMock.expect(mockCrewDao.getAllCrewDtoWithCallByDate(startDate, endDate))
                .andReturn(crews);

        EasyMock.replay(mockCrewDao);

        crewService.getAllCrewDtoWithCallByDate(startDate, endDate);
    }

}
