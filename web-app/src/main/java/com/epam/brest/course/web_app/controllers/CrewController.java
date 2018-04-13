package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Crew;
import com.epam.brest.course.dao.DatesRange;
import com.epam.brest.course.dto.CarDto;
import com.epam.brest.course.dto.CrewDtoWithCall;
import com.epam.brest.course.service.CarService;
import com.epam.brest.course.service.CrewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

/**
 * CrewController.
 */
@Controller
public class CrewController {

    /**
     * CrewRestClient.
     */
    @Autowired
    private CrewService crewService;

    /**
     * CarRestClient.
     */
    @Autowired
    private CarService carService;

    /**
     * Logger.
     */
    private final static Logger LOGGER = LogManager.getLogger();

    @GetMapping(value = "/crews")
    public final String getCrews(final Model model) {
        LOGGER.debug("Req: getCrews()");
        Collection<CrewDtoWithCall> crews =
                crewService.getAllCrewDtoWithCall();
        LOGGER.debug("Res: getCrews({})", crews);
        model.addAttribute("crews", crews);
        DatesRange datesRange = new DatesRange();
        model.addAttribute("datesRange", datesRange);
        return "crews";
    }

    @GetMapping(value = "/crew")
    public final String getAddCrew(final Model model) {
        LOGGER.debug("Req: getAddCrew()");
        boolean isEdit = false;
        Collection<CarDto> cars = carService.getAllCarsDto();
        LOGGER.debug("Res: getAddCrew({}, {})", isEdit, cars);
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("cars", cars);
        model.addAttribute("crew", new Crew());
        return "crew";
    }

    @PostMapping(value = "/crew")
    public final String AddCrew(@Valid final Crew crew,
                                final BindingResult result,
                                final Model model) {
        LOGGER.debug("Req: addCrew({})", crew);

        if (result.hasErrors()) {
            Collection<CarDto> cars = carService.getAllCarsDto();
            model.addAttribute("isEdit", false);
            model.addAttribute("cars", cars);
            model.addAttribute("crew", crew);
            return "crew";
        } else {
            crewService.addCrew(crew);
            return "redirect:/crews";
        }

    }

    @GetMapping(value = "/editCrew/{id}")
    public final String getUpdateCrew(@PathVariable final int id,
                                      final Model model) {
        LOGGER.debug("Req: getUpdateCrew({})", id);
        boolean isEdit = true;
        Collection<CarDto> cars = carService.getAllCarsDto();
        Crew crew = crewService.getCrewById(id);
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("cars", cars);
        model.addAttribute("crew", crew);
        LOGGER.debug("Res: getUpdateCrew({})", model);
        return "crew";
    }

    @PostMapping(value = "/editCrew/{id}")
    public final String updateCrew(@Valid final Crew crew,
                                   BindingResult result,
                                   Model model) {
        LOGGER.debug("updateCrew({})", crew);
        if (result.hasErrors()) {
            Collection<CarDto> cars = carService.getAllCarsDto();
            model.addAttribute("isEdit", true);
            model.addAttribute("cars", cars);
            model.addAttribute("crew", crew);
            return "crew";
        } else {
            crewService.updateCrew(crew);
            return "redirect:/crews";
        }
    }

    @GetMapping(value = "/crew/{id}/delete")
    public final String deleteCrew(@PathVariable final int id) {
        LOGGER.debug("deleteCrew({})", id);
        crewService.deleteCrewById(id);
        return "redirect:/crews";
    }

    @PostMapping(value = "/filterCrews")
    public final String filterCalls(@Valid final DatesRange datesRange,
                                    final BindingResult result,
                                    final Model model) {
        LOGGER.debug("Req: filterCrews({})", datesRange);
        Collection<CrewDtoWithCall> crews = null;
        if (result.hasErrors()) {
            crews = crewService.getAllCrewDtoWithCall();
        } else {
            crews = crewService.getAllCrewDtoWithCallByDate(datesRange.getDateFrom(),
                    datesRange.getDateTo());
        }
        LOGGER.debug("Res: filterCrews({})", crews);
        model.addAttribute("crews", crews);
        model.addAttribute("datesRange", datesRange);
        return "crews";
    }
}
