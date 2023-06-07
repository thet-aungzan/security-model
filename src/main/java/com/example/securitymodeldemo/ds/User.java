package com.example.securitymodeldemo.ds;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "username can't be empty")
	private String username;
	
	@NotBlank(message = "password can't be empty")
	@Pattern(regexp = "[A-Za-z0-9\\@\\-]*",message = "password contains illagel charaters")
	private String password;
	
	private String role;
	
	public User() {}

}
