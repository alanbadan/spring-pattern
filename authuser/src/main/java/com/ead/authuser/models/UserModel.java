package com.ead.authuser.models;

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



import com.ead.authuser.enuns.UserStatus;
import com.ead.authuser.enuns.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;



@Data                                         // pode-se fazer a nivel de atributo     
@JsonInclude(JsonInclude.Include.NON_NULL)  // ele ocupa todos valores nulos, ele ignora campos nulos e mostar soemnte o capos com valores
@Entity
@Table(name = "TB_USER")
public class UserModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID userid;
	@Column(nullable = false, unique = true, length = 50)
	private String username;
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	@Column(nullable = false, length = 250)
	@JsonIgnore   // para ocultar o campo quuando retornar 
	private String password;
	@Column(nullable = false, length = 150)
	private String fullname;
	@Column(length = 20)
	private String phoneNumber;
	@Column(length = 20)
	private String cpf;
	@Column
	private String imagemUrl;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)  // anotacao pra transformar enum em string
	private UserStatus userStatus;
	@Enumerated(EnumType.STRING)
	private UserType userType;
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss") //trazendo o formato da dtae
	private LocalDateTime creationDate;
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime lastUpdatedate;
	
}
