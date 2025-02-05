package com.cts.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.bindings.UserData;
import com.cts.entity.UserEntity;
import com.cts.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user-register")
    public String showPatientRegisterForm(Model model) {
        model.addAttribute("userData", new UserData());
        return "signup";
    }
	
	@PostMapping("/user-register")
    public String registerPatient(@ModelAttribute("userData") UserData userData, Model model) {
        boolean success = userService.registerPatient(userData);

        if (!success) {
            model.addAttribute("errorMessage", "email already registered!");
            return "signup";
        }

        model.addAttribute("successMessage", "Registration successful!");
        return "signup";
    }
	
	 @GetMapping("/user-login")
	 public String showPatientLoginForm(Model model) {
	     model.addAttribute("user", new UserEntity()); 
	     return "signin";
	}	
	 
	 @PostMapping("/user-login")
	 public String patientLogin(@ModelAttribute("user") UserData userData, Model model, HttpSession session) {
	     Optional<UserEntity> userOptional = userService.validateLogin(userData.getEmail(), userData.getPassword());

	     if (userOptional.isPresent() && userOptional.get().getRole().equals("CITIZEN")) {
	        session.setAttribute("loggedInUser", userOptional.get());
	            return "dashboard";  
	        }

	        model.addAttribute("errorMessage", "Invalid username or password!");
	        return "signin";
	    }
	
	 @GetMapping("/admin-login")
	    public String showAdminLoginForm(Model model) {
	        model.addAttribute("user", new UserEntity());
	        return "admin_signin";
	    }

	    @PostMapping("/admin-login")
	    public String adminLogin(@ModelAttribute("user") UserData userData, Model model, HttpSession session) {
	        Optional<UserEntity> userOptional = userService.validateLogin(userData.getEmail(), userData.getPassword());

	        if (userOptional.isPresent() && userOptional.get().getRole().equals("ADMIN")) {
	            session.setAttribute("loggedInUser", userOptional.get()); 
	            System.out.println("Session created for admin");
	            return "admin";
	        }

	        model.addAttribute("errorMessage", "Invalid admin credentials!");
	        return "admin_signin";
	    }
	    
	    @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        if (session == null || session.getAttribute("loggedInUser") == null) {  
	            System.out.println("No active session found.");
	        } else {
	            System.out.println("Session invalidated for user");
	            session.invalidate();  
	        }
	        return "redirect:/"; 
	    }

	    @GetMapping("/dashboard")
	    public String showAdminDashboard(HttpSession session) {
	        UserEntity loggedInUser = (UserEntity) session.getAttribute("loggedInUser");

	        if (loggedInUser == null || !loggedInUser.getRole().equals("ADMIN")) {
	            return "access-denied"; 
	        }

	        return "dashboard"; 
	    }

	 
}
