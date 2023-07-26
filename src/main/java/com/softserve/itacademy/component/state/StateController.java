package com.softserve.itacademy.component.state;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/states")
@RequiredArgsConstructor

public class StateController {

    private final StateService stateService;

    @Secured("ADMIN")
    @GetMapping("")
    public String listStates(Model model) {
        model.addAttribute("states", stateService.findAll());
        return "state/state-list";
    }

}
