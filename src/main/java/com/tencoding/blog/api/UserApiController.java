package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.model.User;
import com.tencoding.blog.service.UserService;

@RestController
public class UserApiController {
	@Autowired
	UserService service;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user){
		user.setRole(RoleType.USER);
		int result = service.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),result);
	}
}
