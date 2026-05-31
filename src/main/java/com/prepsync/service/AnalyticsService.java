package com.prepsync.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.prepsync.dto.DashboardResponse;
import com.prepsync.dto.StreakResponse;
import com.prepsync.dto.WeakAreaResponse;
import com.prepsync.entity.Topic;
import com.prepsync.entity.User;
import com.prepsync.enums.ConfidenceLevel;
import com.prepsync.enums.DifficultyLevel;
import com.prepsync.enums.TopicStatus;
import com.prepsync.repository.SubjectRepository;
import com.prepsync.repository.TopicRepository;
import com.prepsync.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalyticsService 
{
	
	private final TopicRepository topicRepository;
	
	private final UserRepository userRepository;
	
	private final SubjectRepository subjectRepository;
	
	private final StudyService studyService;
	
	public DashboardResponse getDashboardData(
			String email)
	{
		User user=userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User not found"));
		
		long totalSubjects=subjectRepository.countByUser(user);
		

        long totalTopics =
                topicRepository.countByUser(user);

        long completedTopics =
                topicRepository.countByUserAndStatus(
                        user,
                        TopicStatus.COMPLETED
                );

        long pendingTopics =
                topicRepository.countByUserAndStatus(
                        user,
                        TopicStatus.PENDING
                )+
                topicRepository.countByUserAndStatus(user, TopicStatus.INPROGRESS);
        long weakTopics=topicRepository.countByUserAndConfidenceLevel(user,ConfidenceLevel.LOW);

        
        StreakResponse streakData=studyService.getStreakData(email);
        
        long revisionDueTopics =
                topicRepository
                        .countByUserAndNextRevisionDateBefore(
                                user,
                                LocalDate.now()
                        );
        int readinessScore =
                calculateReadiness(
                        totalTopics,
                        completedTopics,
                        weakTopics
                );
        return new DashboardResponse(
                totalSubjects,
                totalTopics,
                completedTopics,
                pendingTopics,
                weakTopics,
                revisionDueTopics,
                streakData.getCurrentStreak(),
                readinessScore
        );
	
	}
	
	
	private int calculateReadiness(
	        long totalTopics,
	        long completedTopics,
	        long weakTopics
	) {

	    if (totalTopics == 0) {
	        return 0;
	    }

	    double completionScore =
	            ((double) completedTopics
	                    / totalTopics) * 100;

	    double weakPenalty =
	            ((double) weakTopics
	                    / totalTopics) * 20;

	    return (int) Math.max(
	            0,
	            completionScore - weakPenalty
	    );
	}
	
	
	public List<WeakAreaResponse>
	getWeakAreas(String email)
	{
		
		
		User user=userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User not found"));
		
		List<Topic> topics=topicRepository.findByUser(user);
		
		List<WeakAreaResponse> weakAreas=new ArrayList<>();
		
		for(Topic topic :topics)
		{
			boolean lowConfidence=topic.getConfidenceLevel()==ConfidenceLevel.LOW;
			
			boolean hardDifficulty=topic.getDifficulty()==DifficultyLevel.HARD;
			
			
			boolean overdue=topic.getNextRevisionDate()!=null &&
					topic.getNextRevisionDate().isBefore(LocalDate.now());
			if(lowConfidence && (hardDifficulty || overdue))
			{
				String reason;
				
				if (hardDifficulty && overdue) {

	                reason =
	                        "Hard topic with overdue revision";

	            }
				else if (hardDifficulty) {

	                reason =
	                        "Low confidence in hard topic";

	            } else {

	                reason =
	                        "Revision overdue";
	            }
				
				
				 weakAreas.add(
		                    new WeakAreaResponse(
		                            topic.getSubject().getName(),
		                            topic.getTopicName(),
		                            reason
		                    )
		            );

			}
		}
		return weakAreas;
		
	}
	

}
