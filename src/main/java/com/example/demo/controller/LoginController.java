package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.request.dto.UserRequestDto;

@RestController
@RequestMapping("/")
public class LoginController {

	@Autowired
	public UserRepository userRepo;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody final UserRequestDto dto){
		User user = userRepo.findByUsernameAndPassword(dto.getUsername(),dto.getPassword());
		System.out.println("user  "+user);
		if(user != null)
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("login successfully");
		else
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("user not found");
	}
}
