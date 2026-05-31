package com.prepsync.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prepsync.dto.StreakResponse;
import com.prepsync.service.StudyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {

	private final StudyService studyService;

    @PostMapping("/mark")
    public ResponseEntity<String>
    markStudy(
            Authentication authentication
    ) {

        String email =
                authentication.getName();

        studyService.markStudySession(email);

        return ResponseEntity.ok(
                "Study session marked"
        );
    }
	
    
    @GetMapping("/streak")
    public ResponseEntity<StreakResponse>
    getStreak(
            Authentication authentication
    ) {

        String email =
                authentication.getName();

        return ResponseEntity.ok(
                studyService.getStreakData(
                        email
                )
        );
    }
	
}
