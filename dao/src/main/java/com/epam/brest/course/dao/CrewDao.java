package com.epam.brest.course.dao;

import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;

import java.sql.Date;
import java.util.Collection;

/**
 * Crew DAO interface.
 */
public interface CrewDao {

    /**
     * Return all crews from database.
     * @return Collection CrewDto.
     */
    Collection<CrewDto> getAllCrewDto();

    /**
     * Return crew by id from database.
     * @param crewId id
     * @return Crew
     */
    Crew getCrewById(int crewId);

    /**
     * Add crew to database.
     * @param crew new crew
     * @return crew with id
     */
    Crew addCrew(Crew crew);

    /**
     * Update crew in database.
     * @param crew crew
     */
    void updateCrew(Crew crew);

    /**
     * Remove crew by id from database.
     * @param crewId crew id
     */
    void deleteCrewById(int crewId);

    /**
     * Return all crews DTO with number of calls.
     * @return Collection CrewDtoWithCall.
     */
    Collection<CrewDtoWithCall> getAllCrewDtoWithCall();

    /**
     * Return all crews DTO with number of calls by date.
     * @param startDate date of first call
     * @param endDate date of last call
     * @return Collection CrewDtoWithCall by date.
     */
    Collection<CrewDtoWithCall> getAllCrewDtoWithCallByDate(
            Date startDate, Date endDate);

}
