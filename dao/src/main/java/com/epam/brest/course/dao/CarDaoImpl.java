package com.epam.brest.course.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collection;

public class CarDaoImpl implements CarDao {

    private String selectAllSql = "SELECT * FROM car";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CarDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Collection<Car> getAllCar() {
        Collection<Car> cars = namedParameterJdbcTemplate
                .getJdbcOperations()
                .query(selectAllSql, BeanPropertyRowMapper.newInstance(Car.class));
        return cars;
    }

    @Override
    public Car getCarById(int carId) {
        return null;
    }

    @Override
    public Car addCar(Car car) {
        return null;
    }

    @Override
    public void updateCar(Car car) {

    }

    @Override
    public void deleteCarById(int id) {

    }
}
