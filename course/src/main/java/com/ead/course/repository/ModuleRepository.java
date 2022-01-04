package com.ead.course.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ead.course.models.ModuleModel;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID>, JpaSpecificationExecutor<ModuleModel> {

	Optional<ModuleModel> findModuleIntoCourse(UUID courseId, UUID moduleId);

	List<ModuleModel> findAllModulesIntoCourse(UUID coureId);
	
	//@Query para select  //para outars acoes: insercao atualizacao ... se usa a anotatiom @Modifying  
	                                           //chave estrangeira
//	@Query(value = "SELECT * FROM TB_MODULES WHERE COURSE_COURSE_ID = :COURSEID ", nativeQuery = true )
//	List<ModuleModel> finAllModulesIntoCourse(@Param("courseId") UUID cousrdeId );
	
// @Query(value = "SELECT * FROM TB_MODULES WHERE COURSE_COURSE_ID = :COURSEID AND MODULE_ID = :MODULE ",nativeQuery = true)
// Optional<ModuleModel> findModuleIntoCourse(UUID courseId, UUID moduleId, @Param("moduleId") UUID courseiD);
}
