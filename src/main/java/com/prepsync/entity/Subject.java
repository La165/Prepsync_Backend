package com.prepsync.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prepsync.enums.DifficultyLevel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name="subjects")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Subject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private boolean completed;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@Enumerated(EnumType.STRING)
	private DifficultyLevel priority;
	private LocalDate dueDate;
}
