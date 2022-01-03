package com.example.demo.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.User;

public interface UserRepository extends CrudRepository<User, String>{

	@Query("SELECT u FROM User u WHERE u.username = ?1 and u.password = ?2")
	User findByUsernameAndPassword(String username,String password);
	
	@Query("FROM User u WHERE u.username = ?1")
	User getUserByContact(String contact);
}
