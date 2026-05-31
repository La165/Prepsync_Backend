package com.prepsync.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prepsync.entity.Subject;
import com.prepsync.entity.User;

public interface SubjectRepository 
extends JpaRepository<Subject, Long>{
	List<Subject> findByUser(User user);
	Optional<Subject> findByNameAndUser(String name,
			User user);
	long countByUser(User user);
}
