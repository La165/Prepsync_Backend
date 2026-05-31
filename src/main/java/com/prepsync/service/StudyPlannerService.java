package com.prepsync.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.prepsync.dto.StudyPlanResponse;
import com.prepsync.entity.Topic;
import com.prepsync.entity.User;
import com.prepsync.enums.ConfidenceLevel;
import com.prepsync.enums.TopicStatus;
import com.prepsync.repository.TopicRepository;
import com.prepsync.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyPlannerService {
	
	private final TopicRepository topicRepository;
	
	private  final UserRepository userRepository;
	
	public StudyPlanResponse generateStudyPlan(String email)
	{
		User user=userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User not found"));
		
		List<Topic> topics=topicRepository.findByUser(user);
		
		List<String> tasks=new ArrayList<>();
		
		for(Topic topic: topics)
		{
			boolean weakTopic=topic.getConfidenceLevel()==ConfidenceLevel.LOW;
			
			boolean revisionDue=topic.getNextRevisionDate()!=null && topic.getNextRevisionDate().isBefore(LocalDate.now());
			
			boolean incomplete=topic.getStatus()!= TopicStatus.COMPLETED;
			
			if(weakTopic && incomplete)
			{
				tasks.add("Revise weak topic:"+topic.getTopicName());
				
			}
			
			if(revisionDue)
			{
				tasks.add("Revision Due: "+topic.getTopicName());
				
			}
		}
		if(tasks.isEmpty())
		{
			tasks.add("Great job! no pending tasks today ");
		}
		return new StudyPlanResponse(LocalDate.now(), tasks);
	}

}
