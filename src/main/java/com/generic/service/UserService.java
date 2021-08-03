package com.generic.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.generic.exceptions.IncompleteFormDataException;
import com.generic.exceptions.ResourceNotFoundException;
import com.generic.exceptions.UserAlreadyExistsException;
import com.generic.model.QueryResponse;
import com.generic.model.User;
import com.generic.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	// get all users
	public QueryResponse getAllUsers() {
		try {
			List<User> users = userRepository.findAll();
			return new QueryResponse(new Date(), "Successful", 200, true, users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new QueryResponse();
	}
	
	// create new user
	public QueryResponse createUser(@RequestBody User user) throws Exception {
		try {
			if(checkUserBody(user) == false) {				
				throw new IncompleteFormDataException("Incomplete form data");
			}
			
			Optional<User> users = userRepository.findByUsername(user.getEmail());
			if(!users.isEmpty() && users.get() != null) {
				throw new UserAlreadyExistsException("User already exists");
			}
			user.setUsername(user.getEmail());
			user.setPassword(bcrypt.encode(user.getPassword()));
			user.setActive(true);
			user.setCreatedAt(new Date());
			user.setUpdatedAt(new Date());
			userRepository.save(user);
			return new QueryResponse(new Date(), "Successful", 201, true, user);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	// update user
	public QueryResponse updateUser(Long id, User user) throws Exception {
		try {
			System.err.println(user.getId());
			Optional<User> users = userRepository.findById(user.getId());
			if(users.isEmpty() || users.get() == null) {
				throw new ResourceNotFoundException("User not found");
			}
			
			user.setPassword(bcrypt.encode(user.getPassword()));
			user.setActive(user.isActive());
			user.setUpdatedAt(new Date());
			userRepository.save(user);
			return new QueryResponse(new Date(), "Successful", 201, true, user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	// method to check request body while creating a new user
		public boolean checkUserBody(User user) {
			if(user.getName().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty() || user.getRoles().isEmpty()) {
				return false;
			}
			return true;
		}
}
