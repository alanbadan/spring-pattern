package com.ead.course.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)  // ele ocupa todos valores nulos, ele ignora campos nulos e mostar soemnte o capos com valores
@Table(name = "TB_MODULOS")
public class ModuleModel implements Serializable{

	private static final long serialVersionUID = 1L; 
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID courseId;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false, length = 250)
	private String descrption;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss") //trazendo o formato da dtae
	@Column(nullable = false)
	private LocalDateTime creationDate;
	
	//varios modulos estaso vinclados com um curso , cria uma cgave estrangeira pra couseid
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //definindo tipo de asseço a serializacao e deserializacao
	@ManyToOne(optional = false, fetch = FetchType.LAZY) //precisa sempre assimilar um modulo a um curso
	private CourseModel course; 
	
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //definindo tipo de asseço a serializacao e deserializacao
	@OneToMany(mappedBy = "module", fetch = FetchType.LAZY) 
	@Fetch(FetchMode.SUBSELECT) //estudar melhor
	private Set<LessonModel> lessos;



}
