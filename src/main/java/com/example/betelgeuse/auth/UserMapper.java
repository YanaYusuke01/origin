package com.example.betelgeuse.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

	@Select("select * from database_name.user_tbl")
	List<User> findAll();
}
