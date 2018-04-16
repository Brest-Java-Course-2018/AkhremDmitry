package com.epam.brest.course.dao;

import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Collection;

/**
 * Crew DAO implementation.
 */
@Component
public class CrewDaoImpl implements CrewDao {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * NamedParameterJdbcTemplate.
     */
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * SQL request for get CrewsDto.
     */
    @Value("${crew.selectAllCrewsDtoSql}")
    private String selectAllCrewsDtoSql;

    /**
     * SQL request for get crew by ID.
     */
    @Value("${crew.selectCrewByIdSql}")
    private String selectCrewByIdSql;

    /**
     * SQL request for insert new crew.
     */
    @Value("${crew.insertCrewSql}")
    private String insertCrewSql;

    /**
     * SQL request for update crew.
     */
    @Value("${crew.updateCrewSql}")
    private String updateCrewSql;

    /**
     * SQL request for delete crew.
     */
    @Value("${crew.deleteCrewByIdSql}")
    private String deleteCrewByIdSql;

    /**
     * SQL request for get all crewDto with number of calls.
     */
    @Value("${crew.selectAllCrewsDtoWithCallSql}")
    private String selectAllCrewsDtoWithCallSql;

    /**
     * SQL request for get all crewDto with number of calls by date.
     */
    @Value("${crew.selectAllCrewsDtoWithCallByDateSql}")
    private String selectAllCrewsDtoWithCallByDateSql;

    @Override
    public final Collection<CrewDto> getAllCrewDto() {
        LOGGER.debug("getAllCrewDto()");
        Collection<CrewDto> crews = namedParameterJdbcTemplate
                .query(selectAllCrewsDtoSql,
                        BeanPropertyRowMapper.newInstance(CrewDto.class));

        return crews;
    }

    @Override
    public final Crew getCrewById(final int crewId) {
        LOGGER.debug("getCrewById({})", crewId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("crewId", crewId);

        Crew crew = namedParameterJdbcTemplate
                .queryForObject(selectCrewByIdSql,
                        namedParameters,
                        BeanPropertyRowMapper.newInstance(Crew.class));
        return crew;
    }

    @Override
    public final Crew addCrew(final Crew crew) {
        LOGGER.debug("addCrew({})", crew);
        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(crew);
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertCrewSql,
                namedParameters, generatedKeyHolder);
        crew.setCrewId(generatedKeyHolder.getKey().intValue());
        return crew;
    }

    @Override
    public final void updateCrew(final Crew crew) {
        LOGGER.debug("updateCrew({})", crew);
        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(crew);
        namedParameterJdbcTemplate.update(updateCrewSql, namedParameters);
    }

    @Override
    public final void deleteCrewById(final int crewId) {
        LOGGER.debug("deleteCrewById({})", crewId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("crewId", crewId);
        namedParameterJdbcTemplate.update(deleteCrewByIdSql, namedParameters);
    }

    @Override
    public final Collection<CrewDtoWithCall> getAllCrewDtoWithCall() {
        LOGGER.debug("getAllCrewDtoWithCall()");
        Collection<CrewDtoWithCall> crews = namedParameterJdbcTemplate
                .getJdbcOperations()
                .query(selectAllCrewsDtoWithCallSql,
                        BeanPropertyRowMapper
                                .newInstance(CrewDtoWithCall.class));
        return crews;
    }

    @Override
    public final Collection<CrewDtoWithCall> getAllCrewDtoWithCallByDate(
            final Date startDate, final Date endDate) {
        LOGGER.debug("getAllCrewDtoWithCallByDate({}, {})",
                startDate, endDate);
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource("startDate", startDate);
        namedParameters.addValue("endDate", endDate);
        Collection<CrewDtoWithCall> crews = namedParameterJdbcTemplate
                .query(selectAllCrewsDtoWithCallByDateSql,
                        namedParameters,
                        BeanPropertyRowMapper
                                .newInstance(CrewDtoWithCall.class));
        return crews;
    }
}
