package com.ead.course.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ead.course.enuns.CourseLevel;
import com.ead.course.enuns.CourseStatus;

import lombok.Data;

@Data
public class CourseDto {

	@NotNull
	private UUID userInstructor;
	@NotBlank
	private String name;
	@NotBlank
	private String descrption;
	private String imagemUrl;
	@NotNull
	private CourseStatus courseStatus;
	@NotNull
	private CourseLevel courseLevel;
	
	
}
