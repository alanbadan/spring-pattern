package com.ead.course.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ead.course.enuns.CourseLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

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
	

}
