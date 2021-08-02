package com.generic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generic.model.JwtRequest;
import com.generic.model.JwtResponse;
import com.generic.model.MyUserDetails;
import com.generic.model.QueryResponse;
import com.generic.model.User;
import com.generic.service.MyUserDetailsService;
import com.generic.service.UserService;
import com.generic.util.JwtUtils;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	// login
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final MyUserDetails userDetails = myUserDetailsService
				.loadUserByUsername(jwtRequest.getUsername());
		final String jwt = jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(jwt));
		
	}

	// get all users
	@GetMapping("/users")
	public QueryResponse getAllUsers() {
		return userService.getAllUsers();
	}
	
	// get user by id
	
	// create new user
	@PostMapping("/users/create")
	public QueryResponse createUser(@RequestBody User user) throws Exception {
		return userService.createUser(user);
	}
	
	// update user
	@PutMapping("/users/{id}")
	public QueryResponse updateUser(@PathVariable Long id, @RequestBody User user) throws Exception {
		return userService.updateUser(id, user);
	}
	
	// delete user
}
