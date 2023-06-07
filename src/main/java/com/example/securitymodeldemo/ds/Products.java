package com.example.securitymodeldemo.ds;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "code can't be empty")
	@Size(min = 2,max = 4,message = "code length 2 - 5 characters.")
	@Pattern(regexp = "[A-Za-z0-9\\-]*",message = "code contain illagel characters.")
	private String code;
	
	@NotBlank(message = "product name can't be empty")
	@Pattern(regexp = "[A-Za-z]*",message = "product name contain illagel charaters.")
	private String productName;
	
	@NotBlank(message = "price cant't be empty")
	private Double price;
	
	
	public Products() {
		// TODO Auto-generated constructor stub
	}
	
}
