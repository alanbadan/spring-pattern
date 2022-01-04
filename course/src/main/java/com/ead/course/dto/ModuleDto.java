package com.ead.course.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ModuleDto {
	
	
	  @NotBlank
	   private String title;
	  @NotBlank
	   private String description;

}
