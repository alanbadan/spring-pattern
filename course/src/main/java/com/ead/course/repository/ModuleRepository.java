package com.ead.course.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ead.course.models.CourseModel;

public interface ModuleRepository extends JpaRepository<CourseModel, UUID> {
	//@Query para select  //para outars acoes: insercao atualizacao ... se usa a anotatiom @Modifying  
	                                           //chave estrangeira
//	@Query(value = "SELECT * FROM TB_MODULES WHERE COURSE_COURSE_ID = :COURSEID ", nativeQuery = true )
//	List<ModuleModel> finAllModulesIntoCourse(@Param("courseId") UUID cousrdeId );
	

}
