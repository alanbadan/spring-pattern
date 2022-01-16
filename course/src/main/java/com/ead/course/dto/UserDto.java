package com.ead.course.dto;

import java.util.UUID;

import com.ead.course.enuns.UserStatus;
import com.ead.course.enuns.UserType;

import lombok.Data;

@Data
public class UserDto {
	
	private UUID userId;
	private String userName;
	private String email;
	private String fullName;
	private String phoneNumber;
	private String cpf;
	private String imgamUrl;
	private UserStatus userStatus;
	private UserType userType;

}
