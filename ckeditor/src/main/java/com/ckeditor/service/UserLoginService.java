package com.ckeditor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ckeditor.entity.UserLogin;
import com.ckeditor.repository.UserLoginRepository;

@Service
public class UserLoginService {

	@Autowired
	private UserLoginRepository userLoginRepository;
	
	public boolean Authenticate(String email, String password) {
		UserLogin user = userLoginRepository.findByEmail(email);
		if(user != null && user.getPassword().equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}
}
