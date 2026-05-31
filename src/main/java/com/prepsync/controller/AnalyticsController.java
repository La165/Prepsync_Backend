package com.prepsync.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prepsync.dto.DashboardResponse;
import com.prepsync.dto.StudyPlanResponse;
import com.prepsync.dto.WeakAreaResponse;
import com.prepsync.service.AnalyticsService;
import com.prepsync.service.StudyPlannerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
	
	private final AnalyticsService analyticsService;
	private final StudyPlannerService studyPlannerService;
	
	@GetMapping("/dashboard")
	public ResponseEntity<DashboardResponse> getDashboard(
			Authentication authentication)
	{
		String email=authentication.getName();
		
		return ResponseEntity.ok(
				analyticsService.getDashboardData(email));
	}
	
	@GetMapping("/weak-areas")
	public ResponseEntity<List<WeakAreaResponse>> getWeakAreas
	(Authentication authentication)
	{
		String email=authentication.getName();
		
		return ResponseEntity.ok(analyticsService.getWeakAreas(email));
		}
	
	@GetMapping("/studyplan")
	public ResponseEntity<StudyPlanResponse>  
	getStudyPlan(Authentication authentication)
	{
		String email=authentication.getName();
		return ResponseEntity.ok(studyPlannerService.generateStudyPlan(email));
		
	}
}
