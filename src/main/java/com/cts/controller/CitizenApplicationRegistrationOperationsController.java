package com.cts.controller;

import com.cts.bindings.CitizenAppRegistrationInputs;
import com.cts.service.ApplicationRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CitizenApplicationRegistrationOperationsController {

    @Autowired
    private ApplicationRegistrationService citizenService;

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("citizen", new CitizenAppRegistrationInputs());
        return "redirect:/application_registration";
    }

    @PostMapping("/register")
    public String processForm(@ModelAttribute("citizen") CitizenAppRegistrationInputs citizen, Model model) {
    	    String targetStateName = "california";
            String stateName = citizenService.getStateBySSN(citizen.getSsn());
            if(stateName.equalsIgnoreCase(targetStateName)) {
            model.addAttribute("stateName", stateName);
            return "eligibility";
            }
            else {
            return "application_registration";
            }
             
    }
}
