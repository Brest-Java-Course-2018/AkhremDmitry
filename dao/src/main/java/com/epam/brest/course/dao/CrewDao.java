package com.epam.brest.course.dao;

import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;

import java.util.Collection;

/**
 * Crew DAO interface
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
     * @param id crew id
     */
    void deleteCrewById(int id);

    /**
     * Return all crews DTO with number of calls.
     * @return Collection CrewDtoWithCall.
     */
    Collection<CrewDtoWithCall> getAllCrewDtoWithCall();

}
