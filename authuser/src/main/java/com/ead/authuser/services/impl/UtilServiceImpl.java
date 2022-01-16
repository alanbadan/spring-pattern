package com.ead.authuser.services.impl;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.ead.authuser.services.UtilService;

public class UtilServiceImpl implements UtilService {

	@Override
	public String createUrl(UUID userId, Pageable pageable) {
		
		String RESQUEST_URI = "";
		
		return RESQUEST_URI +"/courses?userId"+ userId+ "&page="+ pageable.getPageNumber()+ "size="+
		pageable.getPageSize()+ "sort="+ pageable.getSort().toString().replaceAll(":", ",");
	}
   
}
