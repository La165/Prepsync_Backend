package com.prepsync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prepsync.dto.ResourceRequest;
import com.prepsync.entity.Resource;
import com.prepsync.entity.Subject;
import com.prepsync.entity.User;
import com.prepsync.repository.ResourceRepository;
import com.prepsync.repository.SubjectRepository;
import com.prepsync.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResourceService {
	
	private final ResourceRepository resourceRepository;
	
	private final UserRepository userRepository;
	private final SubjectRepository subjectRepository;
	
	public Resource addResource(String email,
			ResourceRequest  request)
	{
		User user=userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		Subject subject=subjectRepository.findById(request.getSubjectId())
				.orElseThrow(()-> new RuntimeException("Subject  not found"));
		Resource resource=new Resource();
		
		resource.setTitle(request.getTitle());
		resource.setDescription(request.getDescription());
		resource.setResourceLink(request.getResourceLink());
		resource.setSubject(subject);
		
		resource.setUser(user);
		return resourceRepository.save(resource);
		
	}
	
	public List<Resource> getResources(String email)
	{
		
		  User user=userRepository.findByEmail(email)
				  .orElseThrow(() -> new RuntimeException("User not found"));
		  
		  return resourceRepository.findByUser(user);
	}

}
