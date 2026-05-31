package com.prepsync.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prepsync.entity.Resource;
import com.prepsync.entity.User;

public interface ResourceRepository  extends JpaRepository<Resource, Long>{
	List<Resource> findByUser(User User);

}
