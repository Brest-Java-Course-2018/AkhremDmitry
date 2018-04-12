package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dao.Call;
import com.epam.brest.course.dto.CrewDto;
import com.epam.brest.course.service.CallService;
import com.epam.brest.course.service.CrewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public final String getCalls (final Model model){
        LOGGER.debug("Req: getCalls()");
        Collection<Call> calls =
                callService.getAllCall();
        LOGGER.debug("Res: getCalls({})", calls);
        model.addAttribute("calls", calls);
        return "calls";
    }

    @GetMapping(value = "/call")
    public final String getAddCall(final Model model){
        LOGGER.debug("getAddCall()");
        Collection<CrewDto> crews = crewService.getAllCrewDto();
        boolean isEdit = false;
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("crews", crews);
        model.addAttribute("call", new Call());
        return "call";
    }

    @PostMapping(value = "/call")
    public final String addCall(final Call call){
        LOGGER.debug("addCall({})", call);
        callService.addCall(call);
        return "redirect:/calls";
    }
}
