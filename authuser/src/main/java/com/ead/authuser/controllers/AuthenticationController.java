package com.ead.authuser.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.enuns.UserStatus;
import com.ead.authuser.enuns.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)// relativo ao cors9 da acesso de qualquer lugra),a nivel de classe tendo acesso a todos os metodos
@RequestMapping( "/auth")
public class AuthenticationController {
	
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<Object> registerUser(@RequestBody  @Validated(UserDto.UserView.RegistrationPost.class)
			                                   @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto) {
		
		if(userService.existByUserName(userDto.getUsername())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR : usuario existente");
		}
		if(userService.existByEmail(userDto.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR : email existente");
		}
		//precisa converter o user dto em usermodel 
		// criando um instancia de usermodel(BeanUtils)
		var userModel = new UserModel();
		BeanUtils.copyProperties(userDto, userModel);
		userModel.setUserStatus(UserStatus.ACTVE);
		userModel.setUserType(UserType.STUDENT);
		userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		userModel.setLastUpdatedate(LocalDateTime.now(ZoneId.of("UTC")));
		userService.save(userModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
		
		
	}
	
}
