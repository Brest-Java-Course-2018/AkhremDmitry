package com.epam.brest.course.dao;

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

@Component
public class CallDaoImpl implements CallDao {

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
     * SQL request for get call by ID.
     */
    @Value("${call.selectCallByIdSql}")
    private String selectCallByIdSql;

    /**
     * SQL request for insert new call.
     */
    @Value("${call.insertCallSql}")
    private String insertCallSql;

    /**
     * SQL request for update call.
     */
    @Value("${call.updateCallSql}")
    private String updateCallSql;

    /**
     * SQL request for delete call.
     */
    @Value("${call.deleteCallByIdSql}")
    private String deleteCallByIdSql;

    /**
     * SQL request for get all calls.
     */
    @Value("${call.selectAllCallSql}")
    private String selectAllCallSql;

    /**
     * SQL request for get all calls by date.
     */
    @Value("${call.selectAllCallByDateSql}")
    private String selectAllCallByDateSql;


    @Override
    public final Call getCallById(final int callId) {
        LOGGER.debug("getCallById({})", callId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("callId", callId);
        Call call = namedParameterJdbcTemplate
                .queryForObject(selectCallByIdSql,
                        namedParameters,
                        BeanPropertyRowMapper.newInstance(Call.class));
        return call;
    }

    @Override
    public final Call addCall(final Call call) {
        LOGGER.debug("addCall({})", call);
        SqlParameterSource namesParameters =
                new BeanPropertySqlParameterSource(call);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate
                .update(insertCallSql,
                        namesParameters,
                        keyHolder);
        call.setCallId(keyHolder.getKey().intValue());
        return call;
    }

    @Override
    public final void updateCall(final Call call) {
        LOGGER.debug("updateCall({})", call);
        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(call);

        namedParameterJdbcTemplate
                .update(updateCallSql, namedParameters);
    }

    @Override
    public final void deleteCallById(final int callId) {
        LOGGER.debug("deleteCallById({})", callId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("callId", callId);
        namedParameterJdbcTemplate.update(deleteCallByIdSql, namedParameters);

    }

    @Override
    public final Collection<Call> getAllCall() {
        LOGGER.debug("getAllCall()");
        Collection<Call> calls = namedParameterJdbcTemplate
                .query(selectAllCallSql,
                        BeanPropertyRowMapper.newInstance(Call.class));
        return calls;
    }

    @Override
    public final Collection<Call> getAllCallByDate(
            final Date startDate, final Date endDate) {
        LOGGER.debug("getAllCall({}, {})", startDate, endDate);
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource("startDate", startDate);
        namedParameters.addValue("endDate", endDate);
        Collection<Call> calls = namedParameterJdbcTemplate
                .query(selectAllCallByDateSql,
                        namedParameters,
                        BeanPropertyRowMapper.newInstance(Call.class));
        return calls;
    }
}
