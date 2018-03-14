package com.epam.brest.course.dao;

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
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * SQL request for get employees.
     */
    @Value("${employee.getAll}")
    private String employeeGetAll;

    /**
     * SQL request for get employee by Id.
     */
    @Value("${employee.getById}")
    private String employeeGetById;

    /**
     * SQL request for add employee.
     */
    @Value("${employee.add}")
    private String employeeAdd;

    /**
     * SQL request for update employee.
     */
    @Value("${employee.update}")
    private String employeeUpdate;

    /**
     * SQL request for delete employee.
     */
    @Value("${employee.delete}")
    private String employeeDelete;

    /**
     * SQL request for get List employees by departmentId.
     */
    @Value("${employee.getAllByDepartmentId}")
    private String employeeGetAllByDepartmentId;

    @Value("${employee.getNumberEmployeesInDepartment}")
    private String employeeGetNumberEmployeesInDepartment;

    /**
     * Setter for namedParameterJdbcTemplate.
     *
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
    public final List<Employee> getAllEmployeeWhere(final String whereSql,
                                                    final int minSalary,
                                                    final int maxSalary) {
        String employeeGetAllWhere = employeeGetAll + whereSql;
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("minSalary", minSalary)
                        .addValue("maxSalary", maxSalary);
        List<Employee> employees = namedParameterJdbcTemplate
                .query(employeeGetAllWhere,
                        namedParameters,
                        BeanPropertyRowMapper.newInstance(Employee.class));
        return employees;
    }

    @Override
    public final List<Employee> getAllEmployeeByDepartmentId(final int departmentId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentId", departmentId);
        List<Employee> employees = namedParameterJdbcTemplate
                .query(employeeGetAllByDepartmentId,
                        namedParameters,
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

    @Override
    public int getNumberEmployeesInDepartment(int departmentID) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentId", departmentID);
        int numberOfEmployees = namedParameterJdbcTemplate
                .queryForObject(employeeGetNumberEmployeesInDepartment,
                        namedParameters, Integer.class);
        return numberOfEmployees;
    }

}
