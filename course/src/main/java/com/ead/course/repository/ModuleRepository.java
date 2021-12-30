package com.ead.course.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ead.course.models.CourseModel;

public interface ModuleRepository extends JpaRepository<CourseModel, UUID> {

}
