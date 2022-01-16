package com.ead.authuser.dto;

import java.util.UUID;

import com.ead.authuser.enuns.CourseLevel;
import com.ead.authuser.enuns.CourseStatus;

import lombok.Data;

@Data
public class CourseDto {
	
	private UUID courseId;
	private String name;
	private String description;
	private String imagemUrl;
    private CourseStatus courseStatus;
    private CourseLevel courseLevel;
    private UUID userInstructor;
    

}
