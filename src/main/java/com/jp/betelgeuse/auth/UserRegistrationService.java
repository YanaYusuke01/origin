package com.jp.betelgeuse.auth;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationService {

	@Autowired
	private UserRepository userrepository;

	@Transactional(rollbackFor = SQLException.class)
	public void registUser(User user) {
		int result = 0;
		result += userrepository.registUser(user);

		if (result != 1) {
			throw new RuntimeException();
		}
	}

}
