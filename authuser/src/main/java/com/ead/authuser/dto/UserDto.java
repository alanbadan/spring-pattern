package com.ead.authuser.dto;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
//recebendo dados do cliente para cadastrar aula (JsonView - Multiplas Visualizações em APIs)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
	
	//criacao de uma interface para reuso de dto baseado em jsonview( poupa o uso de dto)
	// somete faz validacao para esses campos
	public interface UserView {
		public static interface RegistrationPost{}
		public static interface UserPut{}
		public static interface PasswordPut{}
		public static interface ImputPut{}
	}   
	private UUID userId;
	
	          //pasando a visao onde ele vai validar
	@NotBlank(groups = UserView.RegistrationPost.class)//não permite valores vazios ou null(@notnull permite valores vazios)
	@Size(min = 4, max = 50, groups = UserView.RegistrationPost.class)//tamanho de caracteres
	@JsonView(UserView.RegistrationPost.class)
	private String username;
	
	@NotBlank(groups = UserView.RegistrationPost.class)
	@Email(groups = UserView.RegistrationPost.class)//verifica se esta em padrao
	@JsonView(UserView.RegistrationPost.class)
	private String email;
	
	@NotBlank(groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
	@Size(min = 6, max = 20, groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
	@JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
	private String password;
	
	@NotBlank(groups = UserView.PasswordPut.class)
	@Size(min = 6, max = 20, groups = UserView.PasswordPut.class)
	@JsonView(UserView.PasswordPut.class)
	private String oldPassword;
	

	@JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
	private String fullName;
	
	
	@JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
	private String phoneNumber;
	
	
	@JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
	private String cpf;
	
	@NotBlank(groups = UserView.ImputPut.class)
	@JsonView(UserView.ImputPut.class)
	private String imagemUrl;
	
	

}
