package com.ead.course.servicies.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ead.course.models.CourseModel;
import com.ead.course.repository.CourseRepository;
import com.ead.course.servicies.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	CourseRepository courseRepository;

	@Override
	public CourseModel save(CourseModel courseModel) {
		
		return courseRepository.save(courseModel);
	}

	@Override
	public Optional<CourseModel> findById(UUID courseId) {
		
		return courseRepository.findById(courseId);
	}

	@Override
	public void delete(CourseModel courseModel) {
		
		
	}

    @Override
	public Page<CourseModel> findAll(Specification<CourseModel> spec, Pageable pageable) { // tem que extebder a JpaSep... no repository
		
		return courseRepository.findAll(spec, pageable);
	}
	

}
