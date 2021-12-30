package com.ead.course.servicies.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ead.course.repository.CourseRepository;
import com.ead.course.servicies.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	CourseRepository courseRepository;
	

}
