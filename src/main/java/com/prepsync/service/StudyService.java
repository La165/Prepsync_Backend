package com.prepsync.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.prepsync.dto.StreakResponse;
import com.prepsync.entity.StudySession;
import com.prepsync.entity.User;
import com.prepsync.repository.StudySessionRepository;
import com.prepsync.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyService {
	
	private final StudySessionRepository studyRepo;
	
	private final UserRepository userRepository;
	
	public void markStudySession(String email)
	{
		User user=userRepository.findByEmail(email)
				.orElseThrow(
						()-> new RuntimeException("User not found"));
		
		boolean alreadyMarked=studyRepo.existsByUserAndStudyDate(user, 
				LocalDate.now());
		
		if(!alreadyMarked)
		{
			StudySession session=new StudySession();
			session.setStudyDate(LocalDate.now());
			session.setUser(user);
			studyRepo.save(session);
		}	
	}
	
	public StreakResponse getStreakData(String email)
	{
		User user=userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User not found"));
		List<StudySession> sessions=
				studyRepo.findByUserOrderByStudyDateDesc(user);
		
		
		if (sessions.isEmpty()) {

	        return new StreakResponse(
	                0,
	                0,
	                0
	        );
	    }

		int currentStreak=0;
		int longestStreak=0;
		int tempStreak=1;
		
		LocalDate expectedDate=LocalDate.now();
		
		//current Streak
		for(StudySession session:sessions)
		{
			if(session.getStudyDate().equals(expectedDate))
			{
				currentStreak++;
				expectedDate=expectedDate.minusDays(1);
			}
			
			else
			{
				break;
			}
		}
		//longest streak
		
		
		for (int i = 1; i < sessions.size(); i++) {

	        LocalDate previous =
	                sessions.get(i - 1)
	                .getStudyDate();

	        LocalDate current =
	                sessions.get(i)
	                .getStudyDate();

	        if (previous.minusDays(1)
	                .equals(current)) {

	            tempStreak++;

	        } else {

	            longestStreak =
	                    Math.max(
	                            longestStreak,
	                            tempStreak
	                    );

	            tempStreak = 1;
	        }
		}
		
		
		longestStreak=Math.max(longestStreak, tempStreak);
		
		return new StreakResponse(currentStreak, longestStreak, sessions.size());
		
	}
	
	

}
