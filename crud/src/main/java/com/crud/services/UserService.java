package com.crud.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.crud.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	
	
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUseraId(String userId);
	
	UserDto updateUser(String id,UserDto userDto);
	void deleteUser(String id);
	List<UserDto> getUsers(int page, int limit, String search);

}
