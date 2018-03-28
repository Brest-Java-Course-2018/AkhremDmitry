package com.epam.brest.course.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collection;

public class CarDaoImpl implements CarDao {

    private String selectAllCarsSql = "SELECT carId, registrationPlate, description FROM car";

    private String selectCarByIdSql = "SELECT carId, registrationPlate, description FROM car WHERE carId = :carId";

    private String insertCarSql = "INSERT INTO car (registrationPlate, description) VALUES (:registrationPlate, :description)";

    private String updateCarSql = "UPDATE car SET registrationPlate = :registrationPlate, description = :description WHERE carId = :carId";

    private String deleteCarByIdSql = "DELETE FROM car WHERE carId = :carId";

    private static final Logger LOGGER = LogManager.getLogger();

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CarDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public final Collection<Car> getAllCar() {
        LOGGER.debug("getAllCar()");

        Collection<Car> cars = namedParameterJdbcTemplate
                .getJdbcOperations()
                .query(selectAllCarsSql, BeanPropertyRowMapper.newInstance(Car.class));
        return cars;
    }

    @Override
    public final Car getCarById(final int carId) {
        LOGGER.debug("getCarById({})", carId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("carId", carId);

        Car car = namedParameterJdbcTemplate
                .queryForObject(selectCarByIdSql,
                        namedParameters,
                        BeanPropertyRowMapper.newInstance(Car.class));
        return car;
    }

    @Override
    public final Car addCar(final Car car) {
        LOGGER.debug("addCar({})", car);
        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(car);
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertCarSql,
                namedParameters,
                generatedKeyHolder);
        car.setCarId(generatedKeyHolder.getKey().intValue());
        return car;
    }

    @Override
    public void updateCar(Car car) {
        LOGGER.debug("updateCar({})", car);
        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(car);

        namedParameterJdbcTemplate.update(updateCarSql, namedParameters);
    }

    @Override
    public void deleteCarById(int carId) {
        LOGGER.debug("deleteCarById({})", carId);
        SqlParameterSource namedParamerer =
                new MapSqlParameterSource("carId", carId);

        namedParameterJdbcTemplate.update(deleteCarByIdSql, namedParamerer);
    }
}
