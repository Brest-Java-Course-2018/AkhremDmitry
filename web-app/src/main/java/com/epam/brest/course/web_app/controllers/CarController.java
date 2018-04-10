package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dto.CarDtoWithCrew;
import com.epam.brest.course.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

/**
 * CarController.
 */
@Controller
public class CarController {

    /**
     * CarRestClient.
     */
    @Autowired
    private CarService carService;

    /**
     * Logger.
     */
    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * Setter for carService.
     *
     * @param carService CarService.
     */
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    /**
     * Show cars page.
     *
     * @param model collection of CarDtoWithCrew
     * @return Template name.
     */
    @GetMapping(value = "/cars")
    public final String getCars(final Model model){
        LOGGER.debug("Req: getCars()");
        Collection<CarDtoWithCrew> cars =
                carService.getAllCarsDtoWithCrew();
        LOGGER.debug("Res: getCars({})", cars);
        model.addAttribute("cars", cars);
        return "cars";
    }

    /**
     * Show car add page.
     *
     * @param model Model
     * @return Template name.
     */
    @GetMapping(value = "/car")
    public final String getCarAdd(final Model model){
        LOGGER.debug("getCarAdd()");
        boolean isEdit = false;
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("car", new Car());
        return "car";
    }

    /**
     * Add car to db.
     * @param car Car
     * @return Template name.
     */
    @PostMapping(value = "/car")
    public final String addCar(final Car car){
        LOGGER.debug("Req: addCar({})", car);
        Car resultCar = carService.addCar(car);
        LOGGER.debug("Res: addCar({})", resultCar);
        return "redirect:/cars";
    }

    /**
     *  Show car edit page.
     *
     * @param id carId
     * @param model Model
     * @return Template name.
     */
    @GetMapping(value = "/editCar/{id}")
    public final String getCarUpdate(@PathVariable final int id,
                                     final Model model){
        LOGGER.debug("Req: getCarUpdate({})", id);
        boolean isEdit = true;
        model.addAttribute("isEdit", isEdit);
        Car car = carService.getCarById(id);
        model.addAttribute("car", car);
        LOGGER.debug("Res: getCarUpdate({})", car);
        return "car";
    }

    /**
     * Update car in db.
     *
     * @param car Car
     * @return Template name.
     */
    @PostMapping(value = "/editCar/{id}")
    public final String updateCar(final Car car){
        LOGGER.debug("updateCar({})", car);
        carService.updateCar(car);
        return "redirect:/cars";
    }

    /**
     * Delete car.
     * @param carId carId
     * @return template name
     */
    @GetMapping(value = "/car/{id}/delete")
    public final String deleteCar(@PathVariable(value = "id") final int carId){
        LOGGER.debug("deleteCar({})", carId);
        carService.deleteCarById(carId);
        return "redirect:/cars";
    }
}
