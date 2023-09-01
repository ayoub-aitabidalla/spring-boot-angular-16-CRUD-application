package com.crud.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.exceptions.UserException;
import com.crud.requests.UserRequest;
import com.crud.responses.ErrorMessages;
import com.crud.responses.UserResponse;
import com.crud.services.UserService;
import com.crud.shared.dto.UserDto;

@RestController
@RequestMapping("/users")
//@CrossOrigin(allowedHeaders ="*" ,origins="*")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest )throws Exception
	{
		if(userRequest.getFirstName().isEmpty() || userRequest.getLastName().isEmpty() || userRequest.getEmail().isEmpty() || userRequest.getPassword().isEmpty() || userRequest.getAddress().isEmpty() ||userRequest.getMobile().isEmpty()) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());  
		ModelMapper modelMapper = new ModelMapper(); 
		UserDto userDto = modelMapper.map(userRequest, UserDto.class); 
		UserDto createUser = userService.createUser(userDto);
		UserResponse userResponse = modelMapper.map(createUser, UserResponse.class); 
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED) ;
	}
	
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> getUser(@PathVariable String id)
	{
		UserDto userDto = userService.getUserByUseraId(id);
		ModelMapper modelMapper = new ModelMapper();
		UserResponse userResponse = modelMapper.map(userDto, UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK) ;
		
	}
	
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserResponse> getAllUsers(@RequestParam(value="page") int page, @RequestParam(value="limit")int limit,@RequestParam(value="search")String search){
		List<UserResponse> usersResponse = new ArrayList<>();
		List<UserDto> users = userService.getUsers(page,limit,search);
		
		
		for(UserDto userDto: users)
		{
			ModelMapper modelMapper = new ModelMapper();
			UserResponse user = modelMapper.map(userDto, UserResponse.class);
			usersResponse.add(user);
		}
		
		return usersResponse;
		
	}
	
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest )
	{
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto updateteUser = userService.updateUser(id, userDto);
		UserResponse userResponse = modelMapper.map(updateteUser, UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED) ;
		
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id)
	{
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
	}
	

}
