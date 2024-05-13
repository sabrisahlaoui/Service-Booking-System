package com.codeElevate.serviceBookingSystem.services.authentication;

import com.codeElevate.serviceBookingSystem.dto.SignupRequestDTO;
import com.codeElevate.serviceBookingSystem.dto.UserDto;

public interface AuthService {
	
	UserDto signupClient(SignupRequestDTO signupRequestDTO);
	
	Boolean presentByEmail(String email);
	
	UserDto signupCompany(SignupRequestDTO signupRequestDTO);

}
