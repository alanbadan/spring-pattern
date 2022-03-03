package com.ead.authuser.services.impl;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ead.authuser.services.UtilService;

@Service
public class UtilServiceImpl implements UtilService {

		
		String RESQUEST_URI = " hhttp://localhost:8882";
		
		public String createUrl(UUID userId, Pageable pageable) {
		return RESQUEST_URI + "/courses?userId"+ userId+ "&page="+ pageable.getPageNumber()+ "size="+
		pageable.getPageSize()+ "sort="+ pageable.getSort().toString().replaceAll(":", ",");
	}
   
}
