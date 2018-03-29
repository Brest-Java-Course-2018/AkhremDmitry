package com.epam.brest.course.dao;

import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collection;

/**
 * Crew DAO implementation.
 */
public class CrewDaoImpl implements CrewDao {

    /**
     * Logger
     */
    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * NamedParameterJdbcTemplate.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Constructor CrewDaoImpl
     * @param namedParameterJdbcTemplate namedParameterJdbcTemplate
     */
    public CrewDaoImpl(final NamedParameterJdbcTemplate
                               namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Collection<CrewDto> getAllCrewDto() {
        return null;
    }

    @Override
    public Crew getCrewById(int crewId) {
        return null;
    }

    @Override
    public Crew addCrew(Crew crew) {
        return null;
    }

    @Override
    public void updateCrew(Crew crew) {

    }

    @Override
    public void deleteCrewById(int id) {

    }

    @Override
    public Collection<CrewDtoWithCall> getAllCrewDtoWithCall() {
        return null;
    }
}
