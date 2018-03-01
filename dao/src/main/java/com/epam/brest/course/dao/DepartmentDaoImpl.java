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

public class DepartmentDaoImpl implements DepartmentDao {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    private final String GET_DEPARTMENTS_SQL = "SELECT departmentId, " +
            "departmentName, description FROM department";
    private final String GET_DEPARTMENTS_BY_ID_SQL = "SELECT departmentId, " +
            "departmentName, description FROM department " +
            "WHERE departmentId = :departmentId";
    private final String UPDATE_DEPARTMENT_SQL = "UPDATE department " +
            "SET departmentName = :departmentName, description = :description" +
            " WHERE departmentId = :departmentId";
    private final String DELETE_DEPARTMENT_BY_ID_SQL =
            "DELETE FROM department WHERE departmentId = :departmentId";


    public DepartmentDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
    }

    @Override
    public List<Department> getAllDepartment() {
        List<Department> departments = jdbcTemplate.query(GET_DEPARTMENTS_SQL, new DepartmentRowMapper());
        return departments;
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentId", departmentId);
        try {

            Department department = namedParameterJdbcTemplate
                    .queryForObject(GET_DEPARTMENTS_BY_ID_SQL,
                            namedParameters,
                            new DepartmentRowMapper());
            return department;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Department addDepartment(Department department) {
        int departmentId = simpleJdbcInsert
                .withTableName("department")
                .usingGeneratedKeyColumns("departmentId")
                .executeAndReturnKey(new BeanPropertySqlParameterSource(department)).intValue();
        return getDepartmentById(departmentId);
    }

    @Override
    public void updateDepartment(Department department) {
        namedParameterJdbcTemplate.update(UPDATE_DEPARTMENT_SQL,
                new BeanPropertySqlParameterSource(department));
    }

    @Override
    public void deleteDepartmentById(int id) {
        namedParameterJdbcTemplate.update(DELETE_DEPARTMENT_BY_ID_SQL,
                new MapSqlParameterSource().addValue("departmentId", id));
    }

    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(1));
            department.setDepartmentName(resultSet.getString(2));
            department.setDescription(resultSet.getString(3));
            return department;
        }
    }
}
