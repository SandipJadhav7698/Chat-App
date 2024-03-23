package com.example.demo.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.dto.Userdto;
import com.example.demo.model.User;
import com.example.demo.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {
@Autowired
UserRepository userRepository;

@Override
public ResponseEntity<String> save(Userdto userdto) {
	User user= new User();
	user.setFirstName(userdto.getFirstName());
	user.setLastName(userdto.getLastName());
	user.setUserName(userdto.getUserName());
	user.setPassword(userdto.getPassword());
	user.setEmail(userdto.getEmail());
	userRepository.save(user);
	
	return new ResponseEntity<>("USER ARE SAVED SUCESSFULLY.... ",HttpStatus.OK);
}

@Override
public ResponseEntity<String> login(String userName, String password) {
	Optional<User> user=userRepository.findByuserName(userName);
	System.out.println(user);
	Optional<User> pass=userRepository.findBypassword(password);
	if(user.isPresent() && pass.isPresent()) {
		return new ResponseEntity<>("LOGIN SUCESSFULLY.... ",HttpStatus.OK); 
	}else {
		return new ResponseEntity<>("LOGIN FAILED.... ",HttpStatus.OK); 
	}
	 
	}
}





