package com.cts.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserData {
	
	private String email;
	private String password;
	private String fullName;
	private String gender;
	private Long phoneNumber;
	private LocalDate dob;
	
	
}
