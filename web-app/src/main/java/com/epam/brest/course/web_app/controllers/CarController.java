package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Car;
import com.epam.brest.course.dto.CarDtoWithCrew;
import com.epam.brest.course.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public final String cars(final Model model){
        Collection<CarDtoWithCrew> cars =
                carService.getAllCarsDtoWithCrew();
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
    public final String car(final Model model){
        boolean isEdit = false;
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("car", new Car());
        return "car";
    }

    @PostMapping(value = "/car")
    public final String addCar(final Car car){
        carService.addCar(car);
        return "redirect:/cars";
    }
}
