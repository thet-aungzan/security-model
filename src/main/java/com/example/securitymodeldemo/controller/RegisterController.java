package com.example.securitymodeldemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.securitymodeldemo.dao.UserDao;
import com.example.securitymodeldemo.ds.User;

@Controller
public class RegisterController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/users")
	public ModelAndView users() {
		return new ModelAndView("users","users",userDao.findAll());
	}
	
	@GetMapping("/users/insert")
	public ModelAndView insert() {
		return new ModelAndView("userForm","user",new User());
	}
	
	@PostMapping("/users/insert")
	public String insert(@ModelAttribute @Valid User user,BindingResult bs) {
		if(bs.hasErrors()) {
			return "/users/insert";
		}else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userDao.save(user);
		}
		
		return "redirect:/users";
	}
}
