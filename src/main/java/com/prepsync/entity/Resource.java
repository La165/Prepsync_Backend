package com.prepsync.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="resources")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long id;
	
	
	private String title;
	
	@Column(length=3000)
	private String description;
	
	private String resourceLink;
	
	
	@ManyToOne
	@JoinColumn(name="subject_id")
	private Subject subject;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
}
