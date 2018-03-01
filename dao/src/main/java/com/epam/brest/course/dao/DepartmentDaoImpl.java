package com.epam.brest.course.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Department DAO.
 */

public class DepartmentDaoImpl implements DepartmentDao {

    /**
     * JdbcTemplate.
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * NamedParameterJdbcTemplate.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * SimpleJdbcInsert.
     */
    private SimpleJdbcInsert simpleJdbcInsert;

    /**
     * SQL request for get departments.
     */
    private final String GET_DEPARTMENTS_SQL = "SELECT departmentId, "
            + "departmentName, description FROM department";

    /**
     * SQL request for get department by ID.
     */
    private final String GET_DEPARTMENT_BY_ID_SQL = "SELECT departmentId, "
            + "departmentName, description FROM department "
            + "WHERE departmentId = :departmentId";

    /**
     * SQL request for get department by name.
     */
    private final String GET_DEPARTMENT_BY_NAME_SQL = "SELECT departmentId, "
            + "departmentName, description FROM department "
            + "WHERE departmentName = :departmentName";

    /**
     * SQL request for update department.
     */
    private final String UPDATE_DEPARTMENT_SQL = "UPDATE department "
            + "SET departmentName = :departmentName, description = :description"
            + " WHERE departmentId = :departmentId";

    /**
     * SQL request for delete department.
     */
    private final String DELETE_DEPARTMENT_BY_ID_SQL =
            "DELETE FROM department WHERE departmentId = :departmentId";

    /**
     * Constructor DepartmentDaoImpl.
     * @param dataSource DataSource
     */
    public DepartmentDaoImpl(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
    }

    @Override
    public final List<Department> getAllDepartment() {
        List<Department> departments = jdbcTemplate
                .query(GET_DEPARTMENTS_SQL,
                        new DepartmentRowMapper());
        return departments;
    }

    @Override
    public final Department getDepartmentById(final int departmentId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentId",
                        departmentId);
        try {

            Department department = namedParameterJdbcTemplate
                    .queryForObject(GET_DEPARTMENT_BY_ID_SQL,
                            namedParameters,
                            new DepartmentRowMapper());
            return department;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public final Department getDepartmentByName(
            final String departmentName) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentName",
                        departmentName);
        try {
            Department department = namedParameterJdbcTemplate
                    .queryForObject(GET_DEPARTMENT_BY_NAME_SQL,
                            namedParameters,
                            new DepartmentRowMapper());
            return department;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public final Department addDepartment(final Department department) {
        if (getDepartmentByName(department.getDepartmentName()) != null) {
            return null;
        }
        int departmentId = simpleJdbcInsert
                .withTableName("department")
                .usingGeneratedKeyColumns("departmentId")
                .executeAndReturnKey(new BeanPropertySqlParameterSource(
                        department))
                .intValue();
        return getDepartmentById(departmentId);
    }

    @Override
    public final void updateDepartment(final Department department) {
        namedParameterJdbcTemplate.update(UPDATE_DEPARTMENT_SQL,
                new BeanPropertySqlParameterSource(department));
    }

    @Override
    public final void deleteDepartmentById(final int id) {
        namedParameterJdbcTemplate.update(DELETE_DEPARTMENT_BY_ID_SQL,
                new MapSqlParameterSource().addValue("departmentId", id));
    }

    /**
     * RowMapper.
     */
    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(final ResultSet resultSet, final int i)
                throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(1));
            department.setDepartmentName(resultSet.getString(2));
            department.setDescription(resultSet.getString(2 + 1));
            return department;
        }
    }
}
