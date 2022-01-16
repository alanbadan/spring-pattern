package com.ead.course.servicies;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

public interface UtilService {
	
	String createUrl(UUID courseId, Pageable pageable);

}
