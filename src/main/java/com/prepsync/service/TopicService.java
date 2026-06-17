package com.prepsync.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.prepsync.dto.TopicRequest;
import com.prepsync.entity.Subject;
import com.prepsync.entity.Topic;
import com.prepsync.entity.User;
import com.prepsync.repository.SubjectRepository;
import com.prepsync.repository.TopicRepository;
import com.prepsync.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    public Topic createTopic(TopicRequest request,String email)
    {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));
        
        Subject subject = subjectRepository
                .findById(request.getSubjectId())
                .orElseThrow(() ->
                        new RuntimeException("Subject not found"));

        Topic topic=new Topic();

        topic.setSubject(subject);
        topic.setTopicName(request.getTopicName());
        topic.setDifficulty(request.getDifficulty());
        topic.setStatus(request.getStatus());
        topic.setNotes(request.getNotes());
        topic.setConfidenceLevel(request.getConfidenceLevel());
        topic.setDifficulty(request.getDifficulty());
        if (!subject.getUser().getId().equals(user.getId())) {
            throw new RuntimeException(
                    "Unauthorized subject access"
            );
        }
        topic.setUser(user);
        

        topic.setRevisionCount(0);
        topic.setLastStudiedDate(LocalDate.now());

        topic.setNextRevisionDate(
                LocalDate.now().plusDays(1)
        );


        return topicRepository.save(topic);
    }
    
    public List<Topic> getUserTopics(String email)
    {
    	User user=userRepository.findByEmail(email)
    			.orElseThrow(()-> new RuntimeException("User not found"));
    	return topicRepository.findByUser(user);
    }
    
    public LocalDate calculateNextRevision(int revisionCount)
    {
    	return switch(revisionCount)
    			{
    	case 1-> LocalDate.now().plusDays(3);
    	case 2-> LocalDate.now().plusDays(7);
    	case 3-> LocalDate.now().plusDays(15);
    	case 4-> LocalDate.now().plusDays(30);
    	default-> LocalDate.now().plusDays(45);
    			};
    }
    
     public Topic reviseTopic(Long topicId,
     String email)
     {
    	   User user=userRepository.findByEmail(email)
    			   .orElseThrow(() -> new RuntimeException("user not found"));
    	   
    	   Topic topic=topicRepository.findById(topicId)
    			   .orElseThrow(() -> new RuntimeException("Topic not found"));
    	   
    	   
    	   if(!topic.getUser().getId().equals(user.getId()))
    	   {
    		   throw new RuntimeException("Unauthorised access");
    	   }
    	   
    	   int currentCount =
    		        topic.getRevisionCount() == null
    		        ? 0
    		        : topic.getRevisionCount();

    		int newRevisionCount = currentCount + 1;
    	   topic.setRevisionCount(newRevisionCount);
    	   topic.setLastStudiedDate(LocalDate.now());
    	   topic.setNextRevisionDate(calculateNextRevision(newRevisionCount));
    	   return topicRepository.save(topic);
    	  }
		  public List<Topic> getRevisionQueue(String email)
{
    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    return topicRepository
            .findByUserOrderByNextRevisionDateAsc(user);
}
     
     public List<Topic> getOverdueTopics(
    	        String email
    	) {

    	    User user = userRepository.findByEmail(email)
    	            .orElseThrow(() ->
    	                    new RuntimeException("User not found"));

    	    return topicRepository
    	            .findByUserAndNextRevisionDateBefore(
    	                    user,
    	                    LocalDate.now()
    	            );
    	}
     
     public Topic updateTopic(
    	        Long id,
    	        TopicRequest request,
    	        String email)
    	{
    	    User user = userRepository.findByEmail(email)
    	            .orElseThrow(() ->
    	                    new RuntimeException("User not found"));

    	    Topic topic = topicRepository.findById(id)
    	            .orElseThrow(() ->
    	                    new RuntimeException("Topic not found"));

    	    if (!topic.getUser().getId().equals(user.getId())) {
    	        throw new RuntimeException("Unauthorized");
    	    }

    	    Subject subject = subjectRepository
    	            .findById(request.getSubjectId())
    	            .orElseThrow(() ->
    	                    new RuntimeException("Subject not found"));

    	    topic.setSubject(subject);
    	    topic.setTopicName(request.getTopicName());
    	    topic.setStatus(request.getStatus());
    	    topic.setDifficulty(request.getDifficulty());
    	    topic.setConfidenceLevel(request.getConfidenceLevel());
    	    topic.setNotes(request.getNotes());

    	    return topicRepository.save(topic);
    	}
     
     public void deleteTopic(
    	        Long id,
    	        String email)
    	{
    	    User user = userRepository.findByEmail(email)
    	            .orElseThrow(() ->
    	                    new RuntimeException("User not found"));

    	    Topic topic = topicRepository.findById(id)
    	            .orElseThrow(() ->
    	                    new RuntimeException("Topic not found"));

    	    if (!topic.getUser().getId().equals(user.getId())) {
    	        throw new RuntimeException("Unauthorized");
    	    }

    	    topicRepository.delete(topic);
    	}
     
     
     
     
}