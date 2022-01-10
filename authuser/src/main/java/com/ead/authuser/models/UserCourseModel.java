package com.ead.authuser.models;

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

@Data                                         // pode-se fazer a nivel de atributo     
@JsonInclude(JsonInclude.Include.NON_NULL)  // ele ocupa todos valores nulos, ele ignora campos nulos e mostar soemnte o capos com valores
@Entity
@Table(name = "TB_USER_COURSES") // CRIACAO DA TABELA INTERMEDIRIA PAR A COMUNICACAO SINCRONA 
public class UserCourseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private UserModel user; // relacao com a tabela UseModel pq um aluno pode ter varios cursos bidirecional
	@Column(nullable = false)
	private UUID courseId; //ja existe esse id na tebla course 
}
