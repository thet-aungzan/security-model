package com.example.securitymodeldemo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.securitymodeldemo.dao.UserDao;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername : "+username);
		
		return userDao.findByUsername(username)
				.map(u-> User.withUsername(username)
						.password(u.getPassword())
						.authorities(AuthorityUtils.createAuthorityList(u.getRole()))
						.build())
				.orElseThrow(()-> new UsernameNotFoundException(String.format("This %s is not logged user.",username)));
	}

}
