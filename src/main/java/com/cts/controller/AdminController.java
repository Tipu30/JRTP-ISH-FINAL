package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.bindings.PlanData;
import com.cts.service.AdminService;


@Controller
@RequestMapping("/plan")
public class AdminController {
	
	@Autowired
	private AdminService planService;

    // Show all plans
    @GetMapping("/all")
    public String getAllPlans(Model model) {
        List<PlanData> plans = planService.showAllPlans();
        
        model.addAttribute("plans", plans);
        return "admin"; // Returns a view page
    }

//    // Show plan by ID
//    @GetMapping("/find")
//    public String getPlanById(@RequestParam("planId") Integer planId, Model model) {
//        PlanData planData = planService.showPlanById(planId);
//        model.addAttribute("plan", planData);
//        return "planDetails";
//    }

    // Show registration form
    @GetMapping("/registerplan")
    public String showRegistrationForm(Model model) {
        model.addAttribute("planData", new PlanData());
        return "redirect:/admin";
    }

    // Save a new plan
    @PostMapping("/registerplan")
    public String savePlan(@ModelAttribute PlanData planData, Model model) {
        String message = planService.registerPlan(planData);
        model.addAttribute("message", message);
        return "redirect:/admin";
    }

    // Show update form
    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("planId") Integer planId, Model model) {
        PlanData planData = planService.showPlanById(planId);
        model.addAttribute("planData", planData);
        return "planUpdate";
    }

    // Update a plan
    @PostMapping("/update")
    public String updatePlan(@ModelAttribute PlanData planData, Model model) {
        String message = planService.updatePlan(planData);
        model.addAttribute("message", message);
        return "redirect:/plan/all";
    }

    // Delete a plan
    @GetMapping("/delete")
    public String removePlanByPlanId(@RequestParam("planId") Integer planId, Model model) {
        String message = planService.deletePlan(planId);
        model.addAttribute("message", message);
        return "redirect:/plan/all";
    }

    // Change plan status
    @GetMapping("/status-change")
    public String changeStatusByPlanId(@RequestParam("planId") Integer planId,
                                       @RequestParam("status") String status,
                                       Model model) {
        String message = planService.changePlanStatus(planId, status);
        model.addAttribute("message", message);
        return "redirect:/plan/all";
    }
}
