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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.course.dto.CourseDto;
import com.ead.course.models.CourseModel;
import com.ead.course.servicies.CourseService;
import com.ead.course.specification.SpecifiactionTemplate;


@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

	@Autowired
	CourseService courseService;
	
	
	@PostMapping
	public ResponseEntity<Object> saveCourse(@RequestBody @Valid CourseDto courseDto) {
		var courseModel = new CourseModel();
		BeanUtils.copyProperties(courseDto, courseDto);
		courseModel.setLastUpDate(LocalDateTime.now(ZoneId.of("UTC")));
		courseModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
	
		return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseModel));
		
		
	}
	
	@DeleteMapping("/{courseId}")
	public ResponseEntity<Object> deleteCourse(@PathVariable(value ="courseId") UUID courseId) {
		
		Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
		if(!courseModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Course Not Found");
		}
		courseService.delete(courseModelOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(" Course Deleted Success");

	}
	
	@PutMapping("/{courseId}")
	public ResponseEntity<Object> upDateCourse(@PathVariable(value ="courseId") UUID courseId,
			                                   @RequestBody @Valid CourseDto courseDto) { // ****** tem passar o valid pq senao o jpa ignora as anotacoes no servi 2NotBlanch @notNul
		Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
		if(!courseModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Course Not Found");
		}
		
		
		
		
	//	var CourseModel = courseModelOptional.get();
	//	return ResponseEntity.status(HttpStatus.OK).body(courseService.save(courseModel));
		return null;
	}
	
	@GetMapping
	public ResponseEntity<Page<CourseModel>> getAllCourses(SpecifiactionTemplate.CourseSpec spec,
			                                               @PageableDefault(page = 0,size = 10, sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable) {
		
		return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll(spec, pageable));
		
	}
	@GetMapping("/{courseId}")
	public ResponseEntity<Object> getOneCourses(@PathVariable(value ="courseId") UUID courseId) {
		
		Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
		if(!courseModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Course Not Found");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(courseModelOptional.get());
		
	}
           
}
