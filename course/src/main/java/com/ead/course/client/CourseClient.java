package com.ead.course.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ead.course.dto.UserDto;
import com.ead.course.servicies.UtilService;

@Component
public class CourseClient {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UtilService utilService;
	
    String RESQUEST_URI = "";
	
	//metodo pra montar a requisicao para o ms course 
	public Page<UserDto> getAllUsersByCourse(UUID courseId ,Pageable pageable){
	//	List<UserDto> searchResult = null;
		String url = utilService.createUrl(courseId, pageable);
		return null;
	}
	
}


