package com.codeElevate.serviceBookingSystem.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codeElevate.serviceBookingSystem.dto.SignupRequestDTO;
import com.codeElevate.serviceBookingSystem.dto.UserDto;
import com.codeElevate.serviceBookingSystem.entity.User;
import com.codeElevate.serviceBookingSystem.enums.UserRole;
import com.codeElevate.serviceBookingSystem.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	
	public UserDto signupClient(SignupRequestDTO signupRequestDTO) {
		
		User user = new User();
		
		user.setName(signupRequestDTO.getName());
		user.setLastname(signupRequestDTO.getLastname());
		user.setEmail(signupRequestDTO.getEmail());
		user.setPhone(signupRequestDTO.getPhone());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));
		
		user.setRole(UserRole.CLIENT);
		
		return userRepository.save(user).getDto();
	}
	
	public Boolean presentByEmail(String email) {
		return userRepository.findFirstByEmail(email) != null;
	}
	
	public UserDto signupCompany(SignupRequestDTO signupRequestDTO) {
		
		User user = new User();
		
		user.setName(signupRequestDTO.getName());
		user.setLastname(signupRequestDTO.getLastname());
		user.setEmail(signupRequestDTO.getEmail());
		user.setPhone(signupRequestDTO.getPhone());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));
		
		user.setRole(UserRole.COMPANY);
		
		return userRepository.save(user).getDto();
	}
}
