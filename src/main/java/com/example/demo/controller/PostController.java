package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modal.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.request.dto.PostReequestDto;

@RestController
@RequestMapping(value = "/post")
public class PostController {
	
	@Autowired
	public PostRepository postRepo;
	
	@GetMapping(value = "/insert")
	public ResponseEntity<?> insert(){
		Post post = new Post();
		post.setTitle("Kareena Kapoor turns paparazzo as Saif Ali Khan and Taimur have breakfast in bed; Kangana Ranaut drops a comment");
		post.setContent("Kareena Kapoor gives a glimpse of Saif Ali Khan and Taimur's breakfast in bed morning. The actor also seems to have given a glimpse of Taimur's room.");
		post.setImageUrl("http://localhost:8888/image/getImage/card2.jpg");
		postRepo.save(post);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("posted");
	}
	
	@PostMapping(value = "/getAll")
	public ResponseEntity<?> getAllPosts(){
		List<Post> posts = postRepo.findAll();
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(posts);
	}
}
