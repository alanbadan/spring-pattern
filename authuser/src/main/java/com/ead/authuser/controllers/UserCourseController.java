package com.ead.authuser.controllers;

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

import com.ead.authuser.Clients.UserClient;
import com.ead.authuser.dto.CourseDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserCourseController { // classes que faz uma requisicao sincrona para course para ver quandtos cursos tem um usuario.
 
	@Autowired
	UserClient userClient;
	
	@GetMapping("/user/{userId}/courseId")                                              
	public ResponseEntity<Page<CourseDto>> getALLCourseByUser(@PageableDefault(page = 8, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable,
			                                                  @PathVariable(value = "userId") UUID userId  ){
	 return ResponseEntity.status(HttpStatus.OK).body(userClient.getAllCoursesByUser(userId, pageable));	
  }
}