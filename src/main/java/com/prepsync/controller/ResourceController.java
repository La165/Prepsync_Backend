package com.prepsync.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prepsync.dto.ResourceRequest;
import com.prepsync.entity.Resource;
import com.prepsync.service.ResourceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {
	
	private final ResourceService resourceService;
	
	@PostMapping
	public ResponseEntity<Resource> addResource
	(@RequestBody  ResourceRequest request,
			Authentication authentication)
	{
		String email=authentication.getName();
		
		return ResponseEntity.ok(resourceService.addResource(email, request));
		
	}
	
	@GetMapping
	public ResponseEntity<List<Resource>> getResources
	(Authentication authentication)
	{
		String email=authentication.getName();
		
		return ResponseEntity.ok(resourceService.getResources(email));
	}

}
