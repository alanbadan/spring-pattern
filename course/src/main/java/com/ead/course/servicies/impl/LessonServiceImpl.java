package com.ead.course.servicies.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ead.course.models.LessonModel;
import com.ead.course.repository.LessonRepository;
import com.ead.course.servicies.LessonService;


@Service
public class LessonServiceImpl implements LessonService {

	@Autowired
	LessonRepository lessonRepository;


    @Override
    public Page<LessonModel> findAllByModule(Specification<LessonModel> spec, Pageable pageable) {
        return lessonRepository.findAllByModule(spec, pageable);
    }

	@Override
    public Optional<LessonModel> findLessonIntoModule(UUID moduleId, UUID lessonId) {
        return lessonRepository.findLessonIntoModule(moduleId, lessonId);
    }

	@Override
	public Optional<LessonModel> findById(UUID moduleId) {
		return lessonRepository.findById(moduleId);
	}

	 @Override
	    public LessonModel save(LessonModel lessonModel) {
	        return lessonRepository.save(lessonModel);
	    }

	@Override
	public void delete(LessonModel lessonModel) {
	     lessonRepository.delete(lessonModel);
		
	}

	@Override
	public Optional<LessonModel> findLessonIntoModel(UUID moduleId, UUID lessonId) {
		return lessonRepository.findLessonIntoModule(moduleId, lessonId);
	}
	
}
