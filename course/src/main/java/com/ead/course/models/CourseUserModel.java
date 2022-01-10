package com.ead.course.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_COURSES_USERS")
public class CourseUserModel  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false) //biderrional
//  @JoinColumn(name = "course_id" ) // anotacao para vc colocar o nome especifico na tabela , senão o banco gera.
    private CourseModel course;
	@Column(nullable = false)
	private UUID userId; // id para associar quais alunos esta matricilados em determinado curso

}
