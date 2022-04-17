package com.jp.betelgeuse.auth;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

	public User identifyUser(String mail);

	public int registUser(User user);
}

