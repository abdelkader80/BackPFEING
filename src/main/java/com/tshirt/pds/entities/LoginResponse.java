package com.tshirt.pds.entities;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class LoginResponse {
	private String tokenjwt;
	private UserDetails userDetails;
	List<Role> mesroles;

}
