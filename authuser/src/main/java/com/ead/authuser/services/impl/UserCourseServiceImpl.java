package com.ead.authuser.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ead.authuser.repositories.UserCourseRepository;

@Service
public class UserCourseServiceImpl {
	
	@Autowired
	UserCourseRepository userCourseRepository;

}
