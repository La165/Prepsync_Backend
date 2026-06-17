package com.prepsync.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prepsync.entity.Topic;
import com.prepsync.entity.User;
import com.prepsync.enums.ConfidenceLevel;
import com.prepsync.enums.TopicStatus;

public interface TopicRepository
        extends JpaRepository<Topic, Long> {

    List<Topic> findByUserId(Long userId);
    List<Topic> findByUser(User user);
    
    List<Topic> findByUserAndNextRevisionDateBefore(
            User user,
            LocalDate date
    );
    List<Topic> findByUserOrderByNextRevisionDateAsc(
        User user
);
    
    
 
    long countByUser(User user);
    
    long countByUserAndStatus(
            User user,
            TopicStatus status
    );
    
    long countByUserAndNextRevisionDateBefore(
            User user,
            LocalDate date
    );
    
    long countByUserAndConfidenceLevel(
            User user,
            ConfidenceLevel confidenceLevel
    );
    
    
}