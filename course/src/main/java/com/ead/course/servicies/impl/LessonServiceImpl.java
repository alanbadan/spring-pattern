package com.ead.course.servicies.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ead.course.repository.LessonRepository;
import com.ead.course.servicies.LessonService;


@Service
public class LessonServiceImpl implements LessonService {

	@Autowired
	LessonRepository lessonRepository;
	
}
