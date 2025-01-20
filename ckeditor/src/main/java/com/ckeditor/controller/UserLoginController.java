package com.ckeditor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ckeditor.requests.LoginRequest;
import com.ckeditor.service.UserLoginService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://10.226.25.102:3000", allowCredentials = "true")
public class UserLoginController {

	@Autowired
	private UserLoginService userLoginService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		

		boolean authenticated =  userLoginService.Authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		if(authenticated) {
			System.out.println(loginRequest.getEmail());
			if("admin@admin.com".equals(loginRequest.getEmail())) {
				return ResponseEntity.ok("Login Successfull admin");
			}
			else if("user@user.com".equals(loginRequest.getEmail())){
				return ResponseEntity.ok("Login Successfull user");
			}
			else {
				return ResponseEntity.ok("login successfull");
			}
			
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
		}
	}
	
	
}
