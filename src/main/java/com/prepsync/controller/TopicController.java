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

import com.prepsync.dto.TopicRequest;
import com.prepsync.entity.Topic;
import com.prepsync.service.TopicService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {
	private final TopicService topicService;
	
	@PostMapping
	public ResponseEntity<Topic> createTopic(
			@Valid @RequestBody TopicRequest request,
			Authentication authentication)
	{
		 String email = authentication.getName();

		    return ResponseEntity.ok(
		            topicService.createTopic(request, email)
		    );	}
	
	@GetMapping
	public ResponseEntity<List<Topic>> getTopics(Authentication authentication)
	{
		String email=authentication.getName();
		
		return ResponseEntity.ok(topicService.getUserTopics(email));
	}
	
	@PutMapping("/{id}/revise")
	public ResponseEntity<Topic> reviseTopic(
	        @PathVariable Long id,
	        Authentication authentication
	) {

	    String email = authentication.getName();

	    return ResponseEntity.ok(
	            topicService.reviseTopic(id, email)
	    );
	}
	
	@GetMapping("/overdue")
	public ResponseEntity<List<Topic>>
	getOverdueTopics(
	        Authentication authentication
	) {

	    String email = authentication.getName();

	    return ResponseEntity.ok(
	            topicService.getOverdueTopics(email)
	    );
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Topic> updateTopic(
	        @PathVariable Long id,
	        @Valid @RequestBody TopicRequest request,
	        Authentication authentication)
	{
	    return ResponseEntity.ok(
	            topicService.updateTopic(
	                    id,
	                    request,
	                    authentication.getName()
	            )
	    );
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTopic(
	        @PathVariable Long id,
	        Authentication authentication)
	{
	    topicService.deleteTopic(
	            id,
	            authentication.getName()
	    );

	    return ResponseEntity.ok(
	            "Topic deleted successfully"
	    );
	}

}
