package com.ead.authuser.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)// relativo ao cors9 da acesso de qualquer lugra),a nivel de classe tendo acesso a todos os metodos
@RequestMapping( "/user")
public class userController {
	
	
	@Autowired
	UserService userService;
	
	
	@GetMapping
	public ResponseEntity<List<UserModel>> getAllUser(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Object> getOneUser(@PathVariable(value = "userId") UUID userId) {
		
		Optional<UserModel> userModelOptional = userService.findById(userId);
		if(!userModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hum errou rude");
					
		}
		else {
		       return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
		
	}
	
  }
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value = "userId") UUID userId) {
		Optional<UserModel> userModelOptional = userService.findById(userId);
		if(!userModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user Not Found");
					
		}
		else {
			userService.delete(userModelOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body("User delete succsess");
		}
		      
		
	}

}
