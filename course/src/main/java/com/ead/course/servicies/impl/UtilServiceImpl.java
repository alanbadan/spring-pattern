package com.ead.course.servicies.impl;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ead.course.servicies.UtilService;

@Service
public class UtilServiceImpl implements UtilService {

	@Override
	public String createUrl(UUID courseId, Pageable pageable) {
		
		String RESQUEST_URI = "";
		
		return RESQUEST_URI + "/user?courseId"+ courseId + "&page=" + pageable.getPageNumber() + "size="+
		pageable.getPageSize()+ "sort="+ pageable.getSort().toString().replaceAll(":", ",");
	}
   
}
