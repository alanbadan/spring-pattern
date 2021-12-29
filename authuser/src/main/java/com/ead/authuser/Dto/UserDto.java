package com.ead.authuser.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
//recebendo dados do cliente para cadastrar aula (JsonView - Multiplas Visualizações em APIs)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
	
	//criacao de uma interface para reuso de dto baseado em jsonview
	// somete faz validacao para esses campos
	public interface UserView {
		public static interface RegistrationPost{}
		public static interface UserPut{}
		public static interface PasswordPut{}
		public static interface ImputPut{}
	}
	@JsonView(UserView.RegistrationPost.class)
	private String username;
	
	@JsonView(UserView.RegistrationPost.class)
	private String email;
	
	@JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
	private String password;
	
	@JsonView(UserView.PasswordPut.class)
	private String oldPassword;
	
	@JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
	private String fullName;
	
	@JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
	private String phoneNumber;
	
	@JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
	private String cpf;
	
	@JsonView(UserView.ImputPut.class)
	private String imagemUrl;
	
	

}
