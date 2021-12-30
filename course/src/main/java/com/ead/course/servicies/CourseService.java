package com.ead.course.servicies;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ead.course.models.CourseModel;

public interface CourseService {

	CourseModel save(CourseModel courseModel);

	Optional<CourseModel> findById(UUID courseId);

	void delete(CourseModel courseModel);

	List<CourseModel> findAll();

}
