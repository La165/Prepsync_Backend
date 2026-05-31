package com.prepsync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardResponse {
	
	private long totalSubjects;
	private long totalTopics;
	private long completedTopics;
	private long pendingTopics;
	private long weakTopics;
	private  long revisionDueTopics;
	private int studyStreak;
	private int readinessScore;

}
