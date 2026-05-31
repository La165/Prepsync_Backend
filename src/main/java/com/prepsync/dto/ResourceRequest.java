package com.prepsync.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResourceRequest {
	 @NotBlank(message = "Title is required")
	    private String title;

	    private String description;

	    @NotBlank(message = "Resource link is required")
	    private String resourceLink;

	    @NotNull(message = "Subject id is required")
	    private Long subjectId;
}
