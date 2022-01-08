package com.tshirt.pds.controleur;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tshirt.pds.entities.LoginRequest;
import com.tshirt.pds.entities.LoginResponse;
import com.tshirt.pds.entities.Produit;
import com.tshirt.pds.entities.Role;
import com.tshirt.pds.entities.User;
import com.tshirt.pds.repository.RoleRepository;
import com.tshirt.pds.repository.UserRepository;
import com.tshirt.pds.security.JwtUtil;
import com.tshirt.pds.service.CustomUserDetailsService;
import com.tshirt.pds.service.UsersService;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    UsersService usersService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired 
    private CustomUserDetailsService  customUserDetailsService;
    
    @Autowired 
    private UserRepository  userRepository;
    
    @PostMapping("/adduser")
    public ResponseEntity<Object> adduser(@RequestBody User user) throws Exception {        
    	User useradded=null;
        try {
        	useradded=usersService.adduser(user);
        } catch (Exception e) {
            ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(useradded);
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody User findByUsername(@RequestParam("username") String userName) {
        return usersService.loadUserbyUsername(userName); 
    }
    @PreAuthorize("hasRole('Administrateur')")
	 @GetMapping("/listusers")
	  public List<User> getusers(){
		  return usersService.getallusers();
	  }
   
  
    

    @PostMapping("/authenticate")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) throws Exception {        
    	LoginResponse loginResponse = new LoginResponse();
        try {
        	 final Authentication authentication = authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(
                    		 loginRequest.getLogin(),
                    		 loginRequest.getPassword()
                     )
             );
             SecurityContextHolder.getContext().setAuthentication(authentication);
             final String token = jwtUtil.generateToken(authentication);
             loginResponse.setTokenjwt(token);
             return ResponseEntity.ok().body(loginResponse);
        } catch (Exception e) {
            ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(loginResponse);
    }
}