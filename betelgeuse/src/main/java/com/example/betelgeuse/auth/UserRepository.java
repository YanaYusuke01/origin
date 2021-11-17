package com.example.betelgeuse.auth;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

	public User identtifyUser(String user_id);
}

