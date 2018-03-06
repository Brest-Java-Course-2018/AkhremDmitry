package com.epam.brest.course.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Department DAO.
 */

public class DepartmentDaoImpl implements DepartmentDao {
    /**
     * departmentId.
     */
    public static final String DEPARTMENT_ID = "departmentId";

    /**
     * departmentName.
     */
    public static final String DEPARTMENT_NAME = "departmentName";

    /**
     * description.
     */
    public static final String DESCRIPTION = "description";

    /**
    * SQL request for get departments.
    */
    @Value("${department.get}")
    private String departmentGet;

    /**
     * SQL request for get department by ID.
     */
    @Value("${department.getById}")
    private String departmentGetById;

    /**
     * SQL request for update department.
     */
    @Value("${department.update}")
    private String departmentUpdate;

    /**
     * SQL request for delete department.
     */
    @Value("${department.deleteById}")
    private String departmentDeleteById;

    /**
     * SQL request for check department.
     */
    @Value("${department.check}")
    private String departmentCheck;

    /**
     * SQL request for insert department.
     */
    @Value("${department.insert}")
    private String departmentInsert;


    /**
     * NamedParameterJdbcTemplate.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    /**
     * Setter for namedParameterJdbcTemplate.
     * @param namedParameterJdbcTemplate NamedParameterJdbcTemplate
     */
    public final void setNamedParameterJdbcTemplate(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public final List<Department> getAllDepartment() {
        List<Department> departments = namedParameterJdbcTemplate
                .getJdbcOperations()
                .query(departmentGet, new DepartmentRowMapper());
        return departments;
    }

    @Override
    public final Department getDepartmentById(final int departmentId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentId",
                        departmentId);


        Department department = namedParameterJdbcTemplate
                .queryForObject(departmentGetById,
                        namedParameters,
                        BeanPropertyRowMapper.newInstance(Department.class));
        return department;

    }

    @Override
    public final Department addDepartment(final Department department) {
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentName",
                        department.getDepartmentName());
        int result = namedParameterJdbcTemplate.queryForObject(departmentCheck,
                namedParameters, Integer.class);
        if (result == 0) {
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("departmentName",
                    department.getDepartmentName());
            namedParameters.addValue("description",
                    department.getDescription());

            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate
                    .update(departmentInsert,
                            namedParameters, generatedKeyHolder);
            department.setDepartmentId(generatedKeyHolder.getKey().intValue());


        } else {
            throw new IllegalArgumentException(
                    "Department with the same name already exists in DB.");
        }
        return department;
    }

    @Override
    public final void updateDepartment(final Department department) {
        SqlParameterSource namedParameter =
                new BeanPropertySqlParameterSource(department);
        namedParameterJdbcTemplate.update(departmentUpdate, namedParameter);
    }

    @Override
    public final void deleteDepartmentById(final int departmentId) {
        namedParameterJdbcTemplate.update(departmentDeleteById,
                new MapSqlParameterSource().addValue(
                        "departmentId", departmentId));
    }

    /**
     * RowMapper.
     */
    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(final ResultSet resultSet, final int i)
                throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            department.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
            department.setDescription(resultSet.getString(DESCRIPTION));
            return department;
        }
    }
}
