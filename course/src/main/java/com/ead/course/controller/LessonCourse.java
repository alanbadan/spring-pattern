package com.ead.course.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ead.course.dto.LessonDto;
import com.ead.course.models.LessonModel;
import com.ead.course.servicies.LessonService;
import com.ead.course.servicies.ModuleService;
import com.ead.course.specification.SpecifiactionTemplate;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonCourse {
	
	
	@Autowired
	ModuleService moduleService;
	
	@Autowired
	LessonService lessonService;
	
	
	
	@GetMapping("/modules/{moduleId}/lessons")
	public ResponseEntity<Page<LessonModel>> getAllLesson (@PathVariable(value = "moduleId") UUID moduleId,
	                                                       SpecifiactionTemplate.LessonSpec spec,
	                                                       @PageableDefault(page = 0, size = 10, sort = "lessonId", direction = Sort.Direction.ASC) Pageable pageable) {
		
	
	        return ResponseEntity.status(HttpStatus.OK).body(lessonService.findAllByModule(SpecifiactionTemplate.lessonModuleId(moduleId).and(spec),pageable));                                                
	  }
	
	@GetMapping("/modules/{moduleId}/lessons/{lessonId}")
	public ResponseEntity<Object> getOneLesson (@PathVariable(value = "moduleId") UUID moduleId,
	                                            @PathVariable(value = "lessonId") UUID lessonId) {
		
		Optional<LessonModel> lessonModelOptional = lessonService.findLessonIntoModule(moduleId, lessonId);
		if(!lessonModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson Not Found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(lessonModelOptional.get());
	}
	
	@PostMapping("/modules/{moduleId}/lessons")
	public ResponseEntity<Object> saveLesson (@PathVariable(value = "moduleId") UUID moduleId,
			                                       @RequestBody @Valid  LessonDto lessonDto){//não esqueca do DTO no requestbody
			                                    	   
			Optional<LessonModel> lessonModelOptional = lessonService.findById(moduleId);                                   	   
			if(!lessonModelOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module Not Found");
			}
			    var lessonModel = new LessonModel();
		        BeanUtils.copyProperties(lessonDto, lessonModel);
		        lessonModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
//		        lessonModel.setModule(moduleModelOptional.get());
		        lessonService.save(lessonModel);
			 return ResponseEntity.status(HttpStatus.CREATED).body(lessonModel);

	}	
	@DeleteMapping("/modules/{moduleId}/lessons/{lessonId}")
	public ResponseEntity<Object> deleteLesson (@PathVariable(value = "moduleId") UUID moduleId,
			                                    @PathVariable(value = "lessonId") UUID lessonId) {
		Optional<LessonModel> lessonModelOptional = lessonService.findLessonIntoModule(moduleId, lessonId);
		if(!lessonModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson Not Found");
		}
		lessonService.delete(lessonModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Lesson deleted successfully");
		
	}
	
	@PutMapping("/modules/{moduleId}/lessons/{lessonId}") 
	public ResponseEntity<Object> updateLesson(@PathVariable(value = "moduleId") UUID moduleId,
			                                   @PathVariable(value = "lessonId") UUID lessonId,
			                                   @RequestBody @Valid LessonDto lessonDto){
		Optional<LessonModel> lessonModelOptional = lessonService.findLessonIntoModel(moduleId, lessonId);
		if(!lessonModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson Not Found");
	}
		var lessonModel = new LessonModel();
		BeanUtils.copyProperties(lessonDto, lessonModel);
		lessonModel.setTitle(lessonDto.getTitle());
		lessonModel.setDescrption(lessonDto.getDescription());
		lessonModel.setVideoUrl(lessonDto.getVideoUrl());
		lessonService.save(lessonModel);
		return ResponseEntity.status(HttpStatus.OK).body("delete Success");
	}	
}
