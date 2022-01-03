package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.request.dto.UserRequestDto;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	public UserRepository userRepo;
	
	@GetMapping("/view")
	public String view() {
		return "user view controller";
	}
	
	@PutMapping("/register")
	public ResponseEntity<?> userRegister(@RequestBody final UserRequestDto dto){
		User user = new User();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		String username = dto.getEmail().replaceAll("@gmail.com", "");
		user.setUsername(username);
		user.setPassword(dto.getPassword());
		userRepo.save(user);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("register successfully..!");
	}
	
	@GetMapping("/get/{contact}")
	public ResponseEntity<?> getUser(@PathVariable final String contact){
		User user = userRepo.getUserByContact(contact);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(user);
	}
	
}
