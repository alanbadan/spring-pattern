package com.ead.course.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.ead.course.enuns.CourseLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)  // ele ocupa todos valores nulos, ele ignora campos nulos e mostar soemnte o capos com valores
@Table(name = "TB_COURSE")
public class CourseModel  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID courseId;
	@Column(nullable = false)
	private UUID userInstructor;
	@Column(nullable = false, length = 150)
	private String name;
	@Column(nullable = false, length = 250)
	private String descrption;
	@Column
	private String imagemUrl;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss") //trazendo o formato da dtae
	@Column(nullable = false)
	private LocalDateTime creationDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss") //trazendo o formato da dtae
	@Column(nullable = false)
	private LocalDateTime lastUpDate;
	@Enumerated(EnumType.STRING)  // anotacao pra transformar enum em string
	private CourseLevel courseLevel;
	@Enumerated(EnumType.STRING)  // anotacao pra transformar enum em string
	private CourseLevel courseStatus;
	
	
	
	
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //definindo tipo de asseço a serializacao e deserializacao
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY) // a chave estrangeira course
	@Fetch(FetchMode.SUBSELECT) //estudar melhor
	private Set<ModuleModel> modules; //assiciocao de cousros para modulo //set nalo é ordenado , e não repete//melhor opcao para criacao de colecao para associacao

	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY) //chave estrangeira course
    private Set<CourseUserModel> courseUsers ; 	
	

}
