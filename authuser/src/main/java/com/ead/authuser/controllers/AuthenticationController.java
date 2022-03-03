package com.ead.authuser.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

import lombok.extern.log4j.Log4j2;


@Log4j2  // com essa anotacao não precisa criar instancia logger
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)// relativo ao cors9 da acesso de qualquer lugra),a nivel de classe tendo acesso a todos os metodos
@RequestMapping( "/auth")
public class AuthenticationController {
	
	
//	Logger logger = LoggerFactory.getLogger(AuthenticationController.class); //criando a instancia para log usando o looger factory .class
//	Logger logger = LogManager.getLogger(AuthenticationController.class); // log4j2
	
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<Object> registerUser(@RequestBody  @Validated(UserDto.UserView.RegistrationPost.class)
			                                   @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto) {
		
		//montando um logo para mostrar a vinda do dto
		log.debug("Post registerUser userDto received {}",userDto.toString()); // usando as chaves para trazer diferentes tipos de contextos e não somente tipo primitivo com usando %
		                                                                       // o metodo + o nome ...
		if(userService.existsByUsername(userDto.getUsername())) {
			log.warn("Username {} is Already Taken ", userDto.getUsername());
			return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR : usuario existente");
		}
		if(userService.existsByEmail(userDto.getEmail())) {
			log.warn("Email {} is Already Taken ", userDto.getEmail());
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
		log.debug("Post registerUser userDto save {}",userModel.toString());// mostrando o id salvo do usuario , to String quando não ha relacionameto com outra tabela caso tenha pode dar zica
		log.info("User saved successfuly userid{}", userModel.getUserid()); //log mostrando o id salvo do usuario
		return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
		
		
	}
	// esses strater logging é padrão no spring /para usar o godForJ2 deve excluir no pom.xml Tag: </exclusion> starter-logging 
	//criando um metodo coo ex para ver logs // no arquivo application.yaml outra maneira de definif logs
//	@RequestMapping("/") //logger é usado quando vc cria a instancia na mao sem  a anotacao do lombok.
/*	public String index() {
		 logger.trace("trace"); // é usando para um maior granularidade(muito detalhe)
		 logger.debug("debug"); // é usado para ambiente de dev (acinamento de metodos) 
		 logger.info("Info");  // é usado em prod para ter u controle e não tantod detalhes  (default) o srping ja traz
		 logger.warn("warn"); // traz avisos de processos     (default)
		 logger.error("error"); // // traz os erros boas praticas inceri-los nos blocos try catch 
		
		return " logging Spring";    
		                                //caminho do projeto                                                                       //pacote
		// mostrando o log pelo maven : C:\projetos\projetos_cursos\EAD>mvn spring-boot:run Dspring-boot.arguments=--logging.level.com.ead=trace
	}
*/	
	// com lombok
	@GetMapping("/")
	public String index() {
		 log.trace("trace"); // é usando para um maior granularidade(muito detalhe)
		 log.debug("debug"); // é usado para ambiente de dev (acinamento de metodos) 
		 log.info("Info");  // é usado em prod para ter u controle e não tantod detalhes  (default) o srping ja traz
		 log.warn("warn"); // traz avisos de processos     (default)
		 log.error("error"); // // traz os erros boas praticas inceri-los nos blocos try catch 
		
		 return "Logging Spring Boot..."; 
	}	
}
