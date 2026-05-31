package com.prepsync.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prepsync.dto.SubjectRequest;
import com.prepsync.entity.Subject;
import com.prepsync.service.SubjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {
	
	private final SubjectService subjectService;
	
	@PostMapping
	public ResponseEntity<Subject> createSubject
	( @Valid @RequestBody SubjectRequest request,
			Authentication authentication)
	{
		String email=authentication.getName();
		return ResponseEntity.ok(
				subjectService.createSubject(request, email)
				);
	}
	
	@GetMapping
	public ResponseEntity<List<Subject>> getSubjects(
			Authentication authentication
			)
	{
		String email=authentication.getName();
		return ResponseEntity.ok(subjectService.getUserSubjects(email));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Subject> updateSubject(
	        @PathVariable Long id,
	        @Valid @RequestBody SubjectRequest request,
	        Authentication authentication
	) 
	{

	    String email = authentication.getName();

	    return ResponseEntity.ok(
	            subjectService.updateSubject(id, request, email)
	    );
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(
            @PathVariable Long id,
            Authentication authentication
    ) {

        String email = authentication.getName();

        subjectService.deleteSubject(id, email);

        return ResponseEntity.ok(
                "Subject deleted successfully"
        );
    }

}
