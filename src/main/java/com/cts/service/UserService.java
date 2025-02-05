package com.cts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bindings.UserData;
import com.cts.entity.CitizenAppRegistrationEntity;
import com.cts.entity.UserEntity;
import com.cts.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@PostConstruct 
	public void createDefaultAdmin() {
	        boolean adminExists = userRepository.existsByRole("ADMIN");

	        if(!adminExists) { 
	            UserEntity admin = new UserEntity("admin", "admin123", "ADMIN");
	            userRepository.save(admin);
	            System.out.println("Default Admin User Created: Username - admin, Password - admin123");
	        } else {
	            System.out.println("Admin user already exists. No need to create a new one.");
	        }
	    }
	public boolean isUsernameTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    
    public boolean registerPatient(UserData userData) {
        if (isUsernameTaken(userData.getEmail())) {
            return false; // Registration failed
        }

        UserEntity user = new UserEntity();
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setRole("CITIZEN");

        userRepository.save(user);
        return true; // Registration successful
    }
    
    public Optional<UserEntity> validateLogin(String email, String password) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
           
            if (user.getPassword().equals(password)) { 
                return Optional.of(user);
            }
        }
        return Optional.empty(); 
    }

	
	
	
}


