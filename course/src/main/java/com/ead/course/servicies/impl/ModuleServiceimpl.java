package com.ead.course.servicies.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repository.LessonRepository;
import com.ead.course.repository.ModuleRepository;
import com.ead.course.servicies.ModuleService;

@Service
public class ModuleServiceimpl implements ModuleService {
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	LessonRepository lessonRepository;
    
	
	@Transactional
	@Override
	public void delete(ModuleModel moduleModel) {
//		List<LessonModel> lessonModelList = lessonRepository.findAllLessonIntoModel(moduleModel.getModuleId());
	//	 if (!lessonModelList.isEmpty()){
	//          lessonRepository.deleteAll(lessonModelList);
	//	 }
		 
		 moduleRepository.delete(moduleModel);		
	}
	
	@Override
    public ModuleModel save(ModuleModel moduleModel) {
        return moduleRepository.save(moduleModel);
    }

	@Override
	public Optional<ModuleModel> findModuleIntoCourse(UUID courseId, UUID moduleId) {
		
		return moduleRepository.findModuleIntoCourse(courseId, moduleId);
	}

	@Override
	public List<ModuleModel> findAllByCourse(UUID coureId) {
		
		return moduleRepository.findAllModulesIntoCourse(coureId);
	}

	@Override
	public Optional<ModuleModel> findById(UUID moduleId) {
	
		return moduleRepository.findById(moduleId);
	}

	@Override
	public Page<ModuleModel> findAllByCourse(Specification<ModuleModel> spec, Pageable pageable) {
		
		return moduleRepository.findAll(spec, pageable);
	}

}
