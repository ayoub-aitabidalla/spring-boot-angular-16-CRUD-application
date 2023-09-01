package com.crud.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.entities.UserEntity;
import com.crud.repositories.UserRepository;

import com.crud.services.UserService;

import com.crud.shared.dto.UserDto;
import com.crud.shareds.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils util;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		UserEntity checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser != null)
			throw new RuntimeException("User Already exist !");
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);

		userEntity.setUserId(util.generateStringId(6));
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		UserEntity newuser = userRepository.save(userEntity);
		UserDto userDto = modelMapper.map(newuser, UserDto.class);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public UserDto getUserByUseraId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userEntity, UserDto.class);

		return userDto;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException(id);
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setAddress(userDto.getAddress());
		userEntity.setMobile(userDto.getMobile());
		UserEntity user = userRepository.save(userEntity);
		ModelMapper modelMapper = new ModelMapper();
		UserDto updatedUser = modelMapper.map(user, UserDto.class);
		return updatedUser;

	}

	@Override
	public void deleteUser(String id) {
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException(id);
		userRepository.delete(userEntity);

	}

	@Override
	public List<UserDto> getUsers(int page, int limit, String search) {
		List<UserDto> usersDto = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, limit);
		Page<UserEntity> userPage;
		if (search.isEmpty()) {
			userPage = userRepository.findAllUsers(pageable);
		} else {
			userPage = userRepository.findAllUsersWithTheName(pageable, search);
		}

		List<UserEntity> users = userPage.getContent();
		for (UserEntity userEntity : users) {
			ModelMapper modelMapper = new ModelMapper();
			UserDto user = modelMapper.map(userEntity, UserDto.class);
			usersDto.add(user);

		}
		return usersDto;
	}

	

}
