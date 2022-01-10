package com.ead.course.servicies.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ead.course.repository.CourseUserRepository;


@Service
public class CourseUserServiceImpl {
	
	@Autowired
	CourseUserRepository courseUserRepository;

}
