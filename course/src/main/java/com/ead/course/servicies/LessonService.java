package com.ead.course.servicies;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ead.course.models.LessonModel;

public interface LessonService {


	

	Page<LessonModel> findAllByModule(Specification<LessonModel> and, Pageable pageable);

	Optional<LessonModel> findLessonIntoModule(UUID moduleId, UUID lessonId);

	Optional<LessonModel> findById(UUID moduleId);

	LessonModel save(LessonModel lessonModel);

	void delete(LessonModel lessonModel);

	Optional<LessonModel> findLessonIntoModel(UUID moduleId, UUID lessonId);

}
