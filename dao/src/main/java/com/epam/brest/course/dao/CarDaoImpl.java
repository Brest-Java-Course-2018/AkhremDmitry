package com.epam.brest.course.dao;

import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CarDtoWithCrew;
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

import java.util.Collection;

/**
 * CarDaoImpl.
 */
@Component
public class CarDaoImpl implements CarDao {

    /**
     * SQL request for get carsDto.
     */
    @Value("${car.selectAllCarsDtoSql}")
    private String selectAllCarsDtoSql;

    /**
     * SQL request for get car by ID.
     */
    @Value("${car.selectCarByIdSql}")
    private String selectCarByIdSql;

    /**
     * SQL request for insert new car.
     */
    @Value("${car.insertCarSql}")
    private String insertCarSql;

    /**
     * SQL request for update car.
     */
    @Value("${car.updateCarSql}")
    private String updateCarSql;

    /**
     * SQL request for delete car.
     */
    @Value("${car.deleteCarByIdSql}")
    private String deleteCarByIdSql;

    /**
     * SQL request for get number of cars.
     */
    @Value("${car.selectNumberOfCarsSql}")
    private String selectNumberOfCarsSql;

    /**
     * SQL request for get all carsDto with number of crew.
     */
    @Value("${car.selectAllCarsDtoWithCrew}")
    private String selectAllCarsDtoWithCrew;

    /**
     * SQL request to check the existence of the car.
     */
    @Value("${car.selectNumberOfCarsWithRegistrationPlateSql}")
    private String selectNumberOfCarsWithRegistrationPlateSql;

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * NamedParameterJdbcTemplate.
     */
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public final Collection<CarDto> getAllCarsDto() {
        LOGGER.debug("getAllCar()");

        Collection<CarDto> cars = namedParameterJdbcTemplate
                .getJdbcOperations()
                .query(selectAllCarsDtoSql,
                        BeanPropertyRowMapper.newInstance(CarDto.class));
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
    public final void updateCar(final Car car) {
        LOGGER.debug("updateCar({})", car);
        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(car);

        namedParameterJdbcTemplate.update(updateCarSql, namedParameters);
    }

    @Override
    public final void deleteCarById(final int carId) {
        LOGGER.debug("deleteCarById({})", carId);
        SqlParameterSource namedParamerer =
                new MapSqlParameterSource("carId", carId);

        namedParameterJdbcTemplate.update(deleteCarByIdSql, namedParamerer);
    }

    @Override
    public final int getNumberOfCars() {
        LOGGER.debug("getNumberOfCars()");
        int countCar = namedParameterJdbcTemplate
                .getJdbcOperations()
                .queryForObject(selectNumberOfCarsSql, Integer.class);
        return countCar;
    }

    @Override
    public final Collection<CarDtoWithCrew> getAllCarsDtoWithCrew() {
        LOGGER.debug("getAllCarsDtoWithCrew()");
        Collection<CarDtoWithCrew> cars = namedParameterJdbcTemplate
                .getJdbcOperations()
                .query(selectAllCarsDtoWithCrew,
                        BeanPropertyRowMapper
                                .newInstance(CarDtoWithCrew.class));
        return cars;
    }

    @Override
    public final int checkCar(final String registrationPlate) {
        LOGGER.debug("checkCar({})", registrationPlate);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("registrationPlate",
                        registrationPlate);
        int result = namedParameterJdbcTemplate
                .queryForObject(selectNumberOfCarsWithRegistrationPlateSql,
                        namedParameters, Integer.class);
        return result;
    }


}
