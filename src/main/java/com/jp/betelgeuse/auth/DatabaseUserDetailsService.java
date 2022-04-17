package com.jp.betelgeuse.auth;

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
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

		UserDetails user = userRepository.identifyUser(mail);

		if (user == null) {
			throw new UsernameNotFoundException("DBからユーザ情報の取得に失敗しました。");
		}

		return user;
	}

}
