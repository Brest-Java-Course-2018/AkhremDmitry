package com.epam.brest.course.dao;

import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:dao-test.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class CrewDaoImplTest {

    @Autowired
    private CrewDao crewDao;

    private static final int ID = 1;
    private static final String CREWNAME = "Crew5";
    private static final String DESCRIPTION = "Some description for crew5";
    private static final int CARID = 1;

    @Test
    public void getAllCrewTest() {
        Collection<CrewDto> crews = crewDao.getAllCrewDto();
        System.out.println(crews);
        Assert.assertFalse(crews.isEmpty());
    }

    @Test
    public void getCrewById() {
        Crew expCrew = crewDao.addCrew(new Crew(CREWNAME, DESCRIPTION, CARID));

        Crew actCrew = crewDao.getCrewById(expCrew.getCrewId());

        Assert.assertEquals(expCrew, actCrew);
    }

    @Test
    public void addCrew() {
        Crew expCrew = new Crew(CREWNAME, DESCRIPTION, CARID);
        int numCrewsBefore = crewDao.getAllCrewDto().size();
        Crew actCrew = crewDao.addCrew(expCrew);
        int numCrewsAfter = crewDao.getAllCrewDto().size();

        Assert.assertTrue(numCrewsBefore == numCrewsAfter - 1);
        Assert.assertEquals(CREWNAME, actCrew.getCrewName());
        Assert.assertEquals(DESCRIPTION, actCrew.getDescription());
        Assert.assertEquals(CARID, actCrew.getCarId());
    }

    @Test
    public void updateCrew() {
        Crew expCrew = new Crew(CREWNAME, DESCRIPTION, CARID);

        Crew addedCrew = crewDao.addCrew(new Crew("some Crew", "some description", 2));
        expCrew.setCrewId(addedCrew.getCrewId());

        int numCrewsBefore = crewDao.getAllCrewDto().size();
        crewDao.updateCrew(expCrew);
        int numCrewsAfter = crewDao.getAllCrewDto().size();

        Crew actCrew = crewDao.getCrewById(expCrew.getCrewId());

        Assert.assertTrue(numCrewsBefore == numCrewsAfter);
        Assert.assertEquals(expCrew, actCrew);
    }

    @Test
    public void deleteCrew() {
        Crew crew = new Crew(CREWNAME, DESCRIPTION, CARID);
        crew = crewDao.addCrew(crew);

        int numCrewsBefore = crewDao.getAllCrewDto().size();
        crewDao.deleteCrewById(crew.getCrewId());
        int numCrewsAfter = crewDao.getAllCrewDto().size();

        Assert.assertTrue(numCrewsBefore - 1 == numCrewsAfter);
    }

    @Test
    public void getAllCrewDtoWithCallTest() {
        Collection<CrewDtoWithCall> crews = crewDao.getAllCrewDtoWithCall();
        System.out.println(crews);
        Assert.assertFalse(crews.isEmpty());
        Assert.assertTrue(crews.size() == crewDao.getAllCrewDto().size());
    }


    @Test
    public void getAllCrewDtoWithCallByDateTest() {
        Date startDate = Date.valueOf("2018-3-23");
        Date endDate = Date.valueOf("2018-3-23");

        Collection<CrewDtoWithCall> crews =
                crewDao.getAllCrewDtoWithCallByDate(startDate, endDate);
        Assert.assertFalse(crews.isEmpty());
        Assert.assertTrue(crews.size() == crewDao.getAllCrewDto().size());
    }
}
