package com.tshirt.pds.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tshirt.pds.entities.User;
import com.tshirt.pds.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        return new org.springframework.security.core.userdetails.User
        		(user.getLogin(),user.getPassword(),new ArrayList<>() );
   
     

    
    
    
//   
//	    User user = userRepository.findByLogine(username)
//	            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//
//	    return CustomUserDetailsService.build(user);
//
    	

   
    
    
    
    }
    
}