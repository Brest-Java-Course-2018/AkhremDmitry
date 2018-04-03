package com.epam.brest.course.service;

import com.epam.brest.course.dao.Crew;
import com.epam.brest.course.dao.CrewDao;
import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.Collection;

/**
 * CrewServiceImpl.
 */
public class CrewServiceImpl implements CrewService{

    /**
     * Property crewDao.
     */
    private CrewDao crewDao;

    /**
     * Logger init.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Setter for crewDao.
     * @param crewDao DAO of crew.
     */
    public void setCrewDao(CrewDao crewDao) {
        this.crewDao = crewDao;
    }

    @Override
    public Collection<CrewDto> getAllCrewDto() {
        LOGGER.debug("getAllCrewDto()");
        return crewDao.getAllCrewDto();
    }

    @Override
    public Crew getCrewById(int crewId) {
        LOGGER.debug("getCrewById({})", crewId);
        return crewDao.getCrewById(crewId);
    }

    @Override
    public Crew addCrew(Crew crew) {
        LOGGER.debug("addCrew({})", crew);
        return crewDao.addCrew(crew);
    }

    @Override
    public void updateCrew(Crew crew) {
        LOGGER.debug("updateCrew({})", crew);
        crewDao.updateCrew(crew);
    }

    @Override
    public void deleteCrewById(int crewId) {
        LOGGER.debug("deleteCrewById({})", crewId);
        crewDao.deleteCrewById(crewId);
    }

    @Override
    public Collection<CrewDtoWithCall> getAllCrewDtoWithCall() {
        LOGGER.debug("getAllCrewDtoWithCall()");
        return crewDao.getAllCrewDtoWithCall();
    }

    @Override
    public Collection<CrewDtoWithCall> getAllCrewDtoWithCallByDate(Date startDate, Date endDate) {
        LOGGER.debug("getAllCrewDtoWithCallByDate({}, {})", startDate, endDate);
        return crewDao.getAllCrewDtoWithCallByDate(startDate, endDate);
    }
}
