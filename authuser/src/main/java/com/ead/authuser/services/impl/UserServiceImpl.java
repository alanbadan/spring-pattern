package com.ead.authuser.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;


@Service
public class UserServiceImpl implements UserService {
  
	
	@Autowired 
	UserRepository userRepository;

	@Override
	public List<UserModel> findAll() {
		return userRepository.findAll();// recuperando os usuarios do banco
	}

	@Override
	public Optional<UserModel> findById(UUID userId) {
		return userRepository.findById(userId);
	}

	@Override
	public void delete(UserModel userModel) {
	     userRepository.delete(userModel);
	
		
	}

	@Override
	public void save(UserModel userModel) {
		userRepository.save(userModel);
		
	}

	@Override
	public boolean existByUserName(String username) {
	     return userRepository.existByUserName(username);
	}

	@Override
	public boolean existByEmail(String email) {
	     return userRepository.existByEmail(email);
	}

	
	
}