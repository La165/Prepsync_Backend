package com.prepsync.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.prepsync.dto.SubjectRequest;
import com.prepsync.entity.Subject;
import com.prepsync.entity.User;
import com.prepsync.repository.SubjectRepository;
import com.prepsync.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectService {
	
	
	private final SubjectRepository subjectRepository;
	private final UserRepository userRepository;
	
	
	public Subject createSubject(SubjectRequest request,
			String email)
	{
		User user=userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		Subject subject=new Subject();
		subject.setName(request.getName());

		subject.setCompleted(request.isCompleted());

		subject.setPriority(request.getPriority());

		subject.setDueDate(request.getDueDate());

		subject.setUser(user);
		
		return subjectRepository.save(subject);
		}
	
	public List<Subject> getUserSubjects(String email)
	{
		User user=userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));
		return subjectRepository.findByUser(user);
	}
	
	public Subject updateSubject(Long id,SubjectRequest request,String email)
	{
		User user=userRepository.findByEmail(email)
				.orElseThrow(()->
				new RuntimeException("User not found"));
		
		Subject subject=subjectRepository.findById(id)
				.orElseThrow(()->
				new RuntimeException("Subject not found"));
		if(!subject.getUser().getId().equals(user.getId()))
		{
			throw new RuntimeException("Unauthorized");
		}
		
		subject.setName(request.getName());

		subject.setCompleted(request.isCompleted());

		subject.setPriority(request.getPriority());

		subject.setDueDate(request.getDueDate());
		return subjectRepository.save(subject);
	}

	
	public void deleteSubject(Long id,String email)
	{
		User user=userRepository.findByEmail(email)
				.orElseThrow(()->
				new RuntimeException("user not found"));
		
		Subject subject=subjectRepository.findById(id)
				.orElseThrow(()->new RuntimeException("subject not found"));
		
		if(!subject.getUser().getId().equals(user.getId()))
		{
			throw new RuntimeException("Unauthorized");
		}
		subjectRepository.delete(subject);
	}
	

}
