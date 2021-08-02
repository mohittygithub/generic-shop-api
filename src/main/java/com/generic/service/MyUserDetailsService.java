package com.generic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generic.model.MyUserDetails;
import com.generic.model.User;
import com.generic.repository.UserRepository;



@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> users = userRepository.findByUsername(username);
		User user = users.get();
		if(user == null) {
			throw new UsernameNotFoundException("User not found with username : " + username);
		}
		return new MyUserDetails(user);
	}

}
