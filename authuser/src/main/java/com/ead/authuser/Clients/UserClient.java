package com.ead.authuser.Clients;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ead.authuser.dto.CourseDto;
import com.ead.authuser.services.UtilService;

import antlr.collections.List;

@Component
public class UserClient {// esses pacote Cliente e classe dentro são especificaos para fazer requisicões entre micro-services

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UtilService utilService;
	
	String RESQUEST_URI = "";
	
	//metodo pra montar a requisicao para o ms course 
	public Page<CourseDto> getAllCoursesByUser(UUID userId ,Pageable pageable){
	//	List<CourseDto> searchResult = null;
		String url = utilService.createUrl(userId, pageable);
		return null;
	}
	
}
