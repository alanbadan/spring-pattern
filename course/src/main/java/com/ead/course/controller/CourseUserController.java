package com.ead.course.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ead.course.client.CourseClient;
import com.ead.course.dto.UserDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseUserController { // controller para cominunicacao sincrona com usercourse

	@Autowired
	CourseClient courseClient;
	
    @GetMapping("/course/{courseId}/userId")                                                         
	public ResponseEntity<Page<UserDto>> getALLUserByCourse(@PageableDefault(page = 8, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable,
			                                                  @PathVariable(value = "courseId") UUID courseId  ){
	 return ResponseEntity.status(HttpStatus.OK).body(courseClient.getAllUsersByCourse(courseId, pageable));
	
  }
}
	

