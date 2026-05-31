package com.prepsync.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prepsync.enums.ConfidenceLevel;
import com.prepsync.enums.DifficultyLevel;
import com.prepsync.enums.TopicStatus;

import jakarta.persistence.Column;
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
@Table(name="topics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="subject_id")
	private Subject subject;
	private String topicName;
	
	@Enumerated(EnumType.STRING)
    private TopicStatus status;

	@Enumerated(EnumType.STRING)
    private DifficultyLevel difficulty;
	
	@Enumerated(EnumType.STRING)
	private ConfidenceLevel confidenceLevel;

    @Column(length = 1000)
    private String notes;

    private LocalDate lastStudiedDate;

    private LocalDate nextRevisionDate;
    
    @Column(nullable=false)
    private Integer revisionCount = 0;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
