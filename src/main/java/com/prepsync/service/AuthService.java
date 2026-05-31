package com.prepsync.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prepsync.dto.AuthResponse;
import com.prepsync.dto.LoginRequest;
import com.prepsync.dto.RegisterRequest;
import com.prepsync.entity.User;
import com.prepsync.enums.UserRole;
import com.prepsync.jwt.JwtUtil;
import com.prepsync.repository.UserRepository;

@Service
public class AuthService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	public AuthService(UserRepository userRepository,
			PasswordEncoder passwordEncoder,
			JwtUtil jwtUtil)
	{
		this.userRepository=userRepository;
		this.passwordEncoder=passwordEncoder;
		this.jwtUtil=jwtUtil;
	}

	public String register(RegisterRequest request)
	{
		if(userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists");
		}
		User user=new User();
		user.setName(request.getName());
		
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		user.setRole(UserRole.USER);
		userRepository.save(user);
		return "User registered successfully";
		}
	
	public AuthResponse login(LoginRequest request)
	{
		User user=userRepository.findByEmail(request.getEmail())
				.orElseThrow(()-> new RuntimeException("Invalid email"));
		
		
		boolean isPasswordMatch=passwordEncoder.matches(request.getPassword(),user.getPassword() 
				);
		if(!isPasswordMatch)
		{
			throw new RuntimeException("invalid password");
		}
		return new AuthResponse(
		        jwtUtil.generateToken(user.getEmail())
		);
	}
	
	
	public User getLoggedInUser(String email)
	{
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
	}
	
}
