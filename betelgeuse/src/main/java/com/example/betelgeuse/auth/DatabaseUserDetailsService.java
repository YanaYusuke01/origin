package com.example.betelgeuse.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository; 

	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return userRepository.identtifyUser(user_id);
	}

}
