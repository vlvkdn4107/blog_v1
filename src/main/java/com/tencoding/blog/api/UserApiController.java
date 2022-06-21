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
	UserService userservice;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		// DB x(벨리데이션 체크)...
		System.out.println("UserApiController 호출됨!!");
		user.setRole(RoleType.USER);
		int result = userservice.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
}
