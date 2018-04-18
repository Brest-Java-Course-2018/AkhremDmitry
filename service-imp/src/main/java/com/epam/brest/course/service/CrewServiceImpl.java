package com.epam.brest.course.service;

import com.epam.brest.course.dao.Crew;
import com.epam.brest.course.dao.CrewDao;
import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collection;

/**
 * CrewServiceImpl.
 */
@Service
public class CrewServiceImpl implements CrewService {

    /**
     * Property crewDao.
     */
    @Autowired
    private CrewDao crewDao;

    /**
     * Logger init.
     */
    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * Setter for crewDao.
     *
     * @param crewDao DAO of crew.
     */
    public final void setCrewDao(final CrewDao crewDao) {
        this.crewDao = crewDao;
    }

    @Override
    public final Collection<CrewDto> getAllCrewDto() {
        LOGGER.debug("getAllCrewDto()");
        return crewDao.getAllCrewDto();
    }

    @Override
    public final Crew getCrewById(final int crewId) {
        LOGGER.debug("getCrewById({})", crewId);
        return crewDao.getCrewById(crewId);
    }

    @Override
    public final Crew addCrew(final Crew crew) {
        LOGGER.debug("addCrew({})", crew);
        return crewDao.addCrew(crew);
    }

    @Override
    public final void updateCrew(final Crew crew) {
        LOGGER.debug("updateCrew({})", crew);
        crewDao.updateCrew(crew);
    }

    @Override
    public final void deleteCrewById(final int crewId) {
        LOGGER.debug("deleteCrewById({})", crewId);
        crewDao.deleteCrewById(crewId);
    }

    @Override
    public final Collection<CrewDtoWithCall> getAllCrewDtoWithCall() {
        LOGGER.debug("getAllCrewDtoWithCall()");
        return crewDao.getAllCrewDtoWithCall();
    }

    @Override
    public final Collection<CrewDtoWithCall> getAllCrewDtoWithCallByDate(
            final Date startDate, final Date endDate) {
        LOGGER.debug("getAllCrewDtoWithCallByDate({}, {})", startDate, endDate);
        return crewDao.getAllCrewDtoWithCallByDate(startDate, endDate);
    }
}
