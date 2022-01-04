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

import com.ead.course.dto.ModuleDto;
import com.ead.course.models.CourseModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.servicies.CourseService;
import com.ead.course.servicies.ModuleService;
import com.ead.course.specification.SpecifiactionTemplate;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleCourse {
	
	 @Autowired
	    ModuleService moduleService;

	    @Autowired
	    CourseService courseService;
	

	@PostMapping("/courses/{courseId}/modules")
	public ResponseEntity<Object> saveModule(@PathVariable(value="courseId") UUID courseId,
			                                 @RequestBody @Valid ModuleDto moduleDto){
		Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
		 if(!courseModelOptional.isPresent()){
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found.");
	}
		  var moduleModel = new ModuleModel();
	        BeanUtils.copyProperties(moduleDto, moduleModel);
	        moduleModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
	        moduleModel.setCourse(courseModelOptional.get());
	        moduleService.save(moduleModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(moduleModel);
			                                 
	}
	@DeleteMapping("/courses/{courseId}/modules/{moduleId}")
	public ResponseEntity<Object> deleteModule(@PathVariable(value = "courseId") UUID courseId,
			                                   @PathVariable(value = "moduleId") UUID moduleId){
		Optional<ModuleModel> modulemodelOptional = moduleService.findModuleIntoCourse(courseId, moduleId);
		if(!modulemodelOptional.isPresent()) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this course.");
		}
		moduleService.delete(modulemodelOptional.get());
		 return ResponseEntity.status(HttpStatus.OK).body("Module deleted successfully.");
	}		 
		 
	
	@GetMapping("/courses/{courseId}/modules")
	public ResponseEntity<Page<ModuleModel>> getAllModules(@PathVariable(value = "courseId") UUID courseId,
			                                               SpecifiactionTemplate.ModuleSpec spec,
			                                               @PageableDefault(page = 0, size = 10, sort = "moduleId", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(moduleService.findAllByCourse(SpecifiactionTemplate.moduleCourseId(courseId).and(spec), pageable));
	}
	
	
    @GetMapping("/course/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> getOneModule (@PathVariable (value = "courseId") UUID courseID,
                                                @PathVariable (value = "moduleId") UUID moduleId) {
    	Optional<ModuleModel> moduleModelOptional = moduleService.findModuleIntoCourse(courseID, moduleId);
    		if(!moduleModelOptional.isPresent()) {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MODULE NOT FOUND FOR THIS COURSE");
    		}
    		
    		return ResponseEntity.status(HttpStatus.OK).body(moduleModelOptional.get());
    	}
    
    
    @PutMapping("/courses/{courseId}/modules/(moduleId}")
    public ResponseEntity<Object> upDateModule(@PathVariable(value = "courseId") UUID courseId,
    		                                   @PathVariable(value = "moduleId") UUID moduleId,
    		                                   @RequestBody @Valid ModuleDto moduleDto){
    	
    	Optional<ModuleModel> moduleMopdelOptional = moduleService.findModuleIntoCourse(courseId, moduleId);
    	if(!moduleMopdelOptional.isPresent()) {
    		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this course.");
    	}
 //  	var moduleModel = moduleModelOptional.get();
 //       moduleModel.setTitle(moduleDto.getTitle());
  //      moduleModel.setDescription(moduleDto.getDescription());
 //       moduleService.save(moduleModel);
 //       return ResponseEntity.status(HttpStatus.OK).body(moduleModel);
                                               
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this course.");
  }
} 
