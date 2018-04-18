package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.dao.DatesRange;
import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.service.CallService;
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

@Controller
public class CallController {

    @Autowired
    private CrewService crewService;

    @Autowired
    private CallService callService;

    /**
     * Logger.
     */
    private final static Logger LOGGER = LogManager.getLogger();

    @GetMapping(value = "/calls")
    public final String getCalls(final Model model) {
        LOGGER.debug("Req: getCalls()");
        Collection<Call> calls =
                callService.getAllCall();
        LOGGER.debug("Res: getCalls({})", calls);
        DatesRange datesRange = new DatesRange();
        model.addAttribute("calls", calls);
        model.addAttribute("datesRange", datesRange);
        return "calls";
    }

    @GetMapping(value = "/call")
    public final String getAddCall(final Model model) {
        LOGGER.debug("getAddCall()");
        Collection<CrewDto> crews = crewService.getAllCrewDto();
        boolean isEdit = false;
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("crews", crews);
        model.addAttribute("call", new Call());
        return "call";
    }

    @PostMapping(value = "/call")
    public final String addCall(@Valid final Call call,
                                final BindingResult result,
                                final Model model) {
        LOGGER.debug("addCall({})", call);

        if (result.hasErrors()) {
            Collection<CrewDto> crews = crewService.getAllCrewDto();
            boolean isEdit = false;
            model.addAttribute("crews", crews);
            model.addAttribute("isEdit", isEdit);
            model.addAttribute("call", call);
            return "call";
        } else {
            callService.addCall(call);
            return "redirect:/calls";
        }
    }

    @GetMapping(value = "/editCall/{id}")
    public final String getUpdateCall(@PathVariable final int id,
                                      final Model model) {
        LOGGER.debug("Req: getEditCall({})", id);
        Call call = callService.getCallById(id);
        Collection<CrewDto> crews = crewService.getAllCrewDto();
        boolean isEdit = true;
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("crews", crews);
        model.addAttribute("call", call);
        LOGGER.debug("Res: getEditCall({})", model);
        return "call";
    }

    @PostMapping(value = "/editCall/{id}")
    public final String updateCall(@Valid final Call call,
                                   final BindingResult result,
                                   final Model model) {
        LOGGER.debug("updateCall({})", call);
        if (result.hasErrors()) {
            Collection<CrewDto> crews = crewService.getAllCrewDto();
            boolean isEdit = true;
            model.addAttribute("isEdit", isEdit);
            model.addAttribute("crews", crews);
            model.addAttribute("call", call);
            return "call";
        } else {
            callService.updateCall(call);
            return "redirect:/calls";
        }

    }

    @GetMapping(value = "/call/{id}/delete")
    public final String deleteCall(@PathVariable final int id) {
        LOGGER.debug("deleteCall({})", id);
        callService.deleteCallById(id);
        return "redirect:/calls";
    }

    @PostMapping(value = "/filterCalls")
    public final String filterCalls(@Valid final DatesRange datesRange,
                                    final BindingResult result,
                                    final Model model) {
        LOGGER.debug("Req: filterCalls({})", datesRange);
        Collection<Call> calls;
        if (result.hasErrors()) {
            calls = callService.getAllCall();
        } else {
            calls = callService.getAllCallByDate(datesRange.getDateFrom(),
                            datesRange.getDateTo());
        }
        LOGGER.debug("Res: filterCalls({})", calls);
        model.addAttribute("calls", calls);
        model.addAttribute("datesRange", datesRange);
        return "calls";
    }
}
