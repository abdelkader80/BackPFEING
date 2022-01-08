package com.tshirt.pds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tshirt.pds.entities.User;
import com.tshirt.pds.repository.UserRepository;

@Service
public class UsersService {
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	
	 public User adduser(User user) {
		 String passwordencoded=bCryptPasswordEncoder.encode(user.getPassword());
		 user.setPassword(passwordencoded);
		 return userRepository.save(user);
		 
	 }
	 
	 public User loadUserbyUsername(String username) {
		 return userRepository.findByLogin(username);
	 }
	 
	
		public List<User> getallusers() {
			
			return userRepository.findAll();
		}
	

}
