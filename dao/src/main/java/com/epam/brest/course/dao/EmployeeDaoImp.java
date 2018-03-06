package com.epam.brest.course.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.util.List;

/**
 * Employee DAO.
 */
public class EmployeeDaoImp implements EmployeeDao {

    /**
     * NamedParameterJdbcTemplate.
     */
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * SQL request for get employees.
     */
    @Value("${employee.GetAll}")
    private String employeeGetAll;

    /**
     * SQL request for get employee by Id.
     */
    @Value("${employee.GetById}")
    private String employeeGetById;

    /**
     * SQL request for add employee.
     */
    @Value("${employee.Add}")
    private String employeeAdd;

    /**
     * SQL request for update employee.
     */
    @Value("${employee.Update}")
    private String employeeUpdate;

    /**
     * SQL request for delete employee.
     */
    @Value("${employee.Delete}")
    private String employeeDelete;

    /**
     * Setter for namedParameterJdbcTemplate.
     * @param namedParameterJdbcTemplate NamedParameterJdbcTemplate
     */
    public final void setNamedParameterJdbcTemplate(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public final List<Employee> getAllEmployee() {
        List<Employee> employees = namedParameterJdbcTemplate
                .getJdbcOperations()
                .query(employeeGetAll,
                        BeanPropertyRowMapper.newInstance(Employee.class));
        return employees;
    }

    @Override
    public final Employee getEmployeeById(final int employeeId) {
        Employee employee;
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("employeeId", employeeId);
        employee = namedParameterJdbcTemplate.
                queryForObject(employeeGetById,
                        namedParameters,
                        BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    @Override
    public final Employee addEmployee(final Employee employee) {
        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(employee);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate
                .update(employeeAdd, namedParameters, keyHolder);
        employee.setEmployeeId(keyHolder.getKey().intValue());
        return employee;
    }

    @Override
    public final void updateEmployee(final Employee employee) {
        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(employee);
        namedParameterJdbcTemplate.update(employeeUpdate, namedParameters);

    }

    @Override
    public final void deleteEmployeeById(final int employeeId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("employeeId", employeeId);
        namedParameterJdbcTemplate.update(employeeDelete, namedParameters);

    }

}
