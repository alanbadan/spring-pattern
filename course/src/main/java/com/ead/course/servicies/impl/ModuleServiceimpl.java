package com.ead.course.servicies.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ead.course.repository.ModuleRepository;
import com.ead.course.servicies.ModuleService;

@Service
public class ModuleServiceimpl implements ModuleService {
	
	@Autowired
	ModuleRepository moduleRepository;

}
