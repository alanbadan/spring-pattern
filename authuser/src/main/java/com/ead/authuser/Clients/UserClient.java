package com.ead.authuser.Clients;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ead.authuser.dto.CourseDto;
import com.ead.authuser.dto.ResponsePageDto;
import com.ead.authuser.services.UtilService;

@Component
public class UserClient {// esses pacote Cliente e classe dentro são especificaos para fazer requisicões entre micro-services
// comunicao sincrona usando A MONTAGEM DA Uri no utilservice onde caso necessite a aplicacao pode usar
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UtilService utilService;
	
	String RESQUEST_URI = " hhttp://localhost:8882";
	
	//metodo pra montar a requisicao para o ms course 
	public Page<CourseDto> getAllCoursesByUser(UUID userId ,Pageable pageable){
		List<CourseDto> searchResult = null;
		String url = utilService.createUrl(userId, pageable);
		
	//contrindo(instanci)  parametrizacao do responsePage dto 
		//classe abstrata do spring core
		ParameterizedTypeReference<ResponsePageDto<CourseDto>> responseType =
				new ParameterizedTypeReference<ResponsePageDto<CourseDto>>() {};
		
    //   Contrindo o reposnse rntity qye vamos resposde e utilaz o restTemplate                         //requestEntity vai ser null
		ResponseEntity<ResponsePageDto<CourseDto>> result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);	
	  //recuperando o content
		searchResult = result.getBody().getContent();
	
		return new PageImpl<>(searchResult);
		
	}
	
}
