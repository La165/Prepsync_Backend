package com.prepsync.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prepsync.dto.AuthResponse;
import com.prepsync.dto.LoginRequest;
import com.prepsync.dto.RegisterRequest;
import com.prepsync.entity.User;
import com.prepsync.jwt.JwtUtil;
import com.prepsync.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
	private final AuthService authService;
	public AuthController(AuthService authService,
			JwtUtil jwtUtil)
	{
		this.authService=authService;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(
			@Valid @RequestBody RegisterRequest request)
	{
		String response=authService.register(request);
		return ResponseEntity.ok(response);
	}
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@Valid @RequestBody LoginRequest request)
	{
		AuthResponse response=authService.login(request);
	
	return ResponseEntity.ok(response);
	}
//	@GetMapping("/profile")
//	public ResponseEntity<User> getLoggedInUser(Authentication authentication)
//	{
//		String email=authentication.getName();
//		
//		User user=authService.getLoggedInUser(email);
//		return ResponseEntity.ok(user);
//	}
	
	@GetMapping("/profile")
	public ResponseEntity<User> getLoggedInUser(
	        HttpServletRequest request)
	{
	    String authHeader =
	            request.getHeader("Authorization");

	    String token =
	            authHeader.substring(7);

	    String email =
	            jwtUtil.extractEmail(token);

	    User user =
	            authService.getLoggedInUser(email);

	    return ResponseEntity.ok(user);
	}
}
