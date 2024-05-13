package com.codeElevate.serviceBookingSystem.dto;

import com.codeElevate.serviceBookingSystem.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {

	private Long id;
	
	private String email;
	
	private String password;
	
	private String name;
	
	private String lastname;
	
	private String phone;
	
	private UserRole role;
}
