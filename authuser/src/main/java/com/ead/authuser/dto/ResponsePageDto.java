package com.ead.authuser.dto;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
//aula composition poattern authuser parte 2

//dto para parametrizas o resposta para o cliente ( no caso no o Page do UserCliente)
public class ResponsePageDto<T> extends PageImpl<T> { 
	// construtor para mostra para o Jackson as parametrizacao
	// anoatando co O Jsoncriator
	// o content vc retira a lista de cursos recebido
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public ResponsePageDto(@JsonProperty("content")List<T> content,
			@JsonProperty("number") int number,
			@JsonProperty("size") int size,
			@JsonProperty("Totalelements") Long TotalElements,
			@JsonProperty("last") boolean last,
			@JsonProperty("TotalPages") int TotalPages,
			@JsonProperty("sort") JsonNode sort,
			@JsonProperty("first") boolean first,
			@JsonProperty("empty") boolean empty) {
		
		super(content, PageRequest.of(number, size), TotalElements);
		
	}
	
//esses contrutores criados automaticamente quando é generico T
    public ResponsePageDto(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public ResponsePageDto(List<T> content) {
        super(content);
    }
}

