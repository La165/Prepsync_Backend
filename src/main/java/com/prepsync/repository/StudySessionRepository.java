package com.prepsync.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prepsync.entity.StudySession;
import com.prepsync.entity.User;

public interface StudySessionRepository  
extends JpaRepository<StudySession,Long>{

	
	 List<StudySession>
	    findByUserOrderByStudyDateDesc(User user);

	    boolean existsByUserAndStudyDate(
	            User user,
	            LocalDate studyDate
	    );
}