package com.ead.authuser.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)// relativo ao cors9 da acesso de qualquer lugra),a nivel de classe tendo acesso a todos os metodos
@RequestMapping( "/user")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	
	@GetMapping                                                // numero de pag. o que buscar , e oredenando
	public ResponseEntity<Page<UserModel>> getAllUser(@PageableDefault(page = 8, sort = "userId", direction = Sort.Direction.ASC)
                                                       Pageable pageable)  {       
	
		Page<UserModel> userModelPage = userService.findAll(pageable);
	
		return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
		
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
	
		@PutMapping("/{userId}")
		public ResponseEntity<Object> updateUser(@PathVariable(value = "userId") UUID userId,
				                                 @RequestBody @Validated(UserDto.UserView.UserPut.class)//anotacao vlidate para validar o uso do json
		                                         @JsonView(UserDto.UserView.UserPut.class) UserDto userDto ) {
			
			Optional<UserModel> userModelOptional = userService.findById(userId);
			if(!userModelOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user Not Found");
						
			}
			else {
				var userModel = userModelOptional.get();
				userModel.setFullname(userDto.getFullName());
				userModel.setPhoneNumber(userDto.getPhoneNumber());
				userModel.setCpf(userDto.getCpf());
				userModel.setLastUpdatedate(LocalDateTime.now(ZoneId.of("UTC")));
				userService.save(userModel);
				
				return ResponseEntity.status(HttpStatus.OK).body(userModel);
			}
		}		
			@PutMapping("/{userId}/password")
			public ResponseEntity<Object> updatePassword(@PathVariable(value = "userId") UUID userId,
					                                     @RequestBody @Validated(UserDto.UserView.PasswordPut.class)
			                                             @JsonView(UserDto.UserView.PasswordPut.class) UserDto userDto ) {
				
				Optional<UserModel> userModelOptional = userService.findById(userId);
				if(!userModelOptional.isPresent()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user Not Found");
							
				}if(userModelOptional.get().getPassword().equals(userDto.getOldPassword())) {
					return ResponseEntity.status(HttpStatus.CONFLICT).body("Senhas não coincidem");
				}
				else {
					var userModel = userModelOptional.get();
					userModel.setPassword(userDto.getPassword());
					userModel.setLastUpdatedate(LocalDateTime.now(ZoneId.of("UTC")));
					userService.save(userModel);
					
					return ResponseEntity.status(HttpStatus.OK).body(" Password atualizado ");
				}
	}
			@PutMapping("/{userId}/imagem")
			public ResponseEntity<Object> updateImage(@PathVariable(value = "userId") UUID userId,
					                                  @RequestBody @Validated(UserDto.UserView.ImputPut.class)
			                                          @JsonView(UserDto.UserView.ImputPut.class) UserDto userDto ) {
				
				Optional<UserModel> userModelOptional = userService.findById(userId);
				if(!userModelOptional.isPresent()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user Not Found");
				}
				else {
					var userModel = userModelOptional.get();
					userModel.setImagemUrl(userDto.getImagemUrl());
					userModel.setLastUpdatedate(LocalDateTime.now(ZoneId.of("UTC")));
					userService.save(userModel);
					
					return ResponseEntity.status(HttpStatus.OK).body(userModel);
				}	
			}
}
