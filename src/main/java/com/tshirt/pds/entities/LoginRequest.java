package com.tshirt.pds.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginRequest {
	private String login;
	private String password;

}
