package com.prepsync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StreakResponse {
	
	private int currentStreak;
	private int longestStreak;
	private long totalStudyDays;

}
