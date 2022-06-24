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
	private UserService userservice;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user){
		user.setRole(RoleType.USER);
		int result = userservice.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
	
	
	
	
//	@PostMapping("/api/user")
//	public ResponseDto<Integer> save(@RequestBody User user) {
//		// DB x(벨리데이션 체크)...
//		System.out.println("UserApiController 호출됨!!");
//		user.setRole(RoleType.USER);
//		int result = userservice.saveUser(user);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
//	}
//	
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user){
//		System.out.println("login 호출됨");
//		// 서비스한테 요청하기
//		// principal : 접근 추제
//		User principal = userservice.login(user);
//		// 여기까지 왔으면 정상적으로 접근 주체가 username, password 확인이 된거다. (세션이라는 거대한 메모리에 저장 해야한다)
//		if(principal != null) {
//			// null이 아닐때만 저장할수있게 방어적 코드
//			httpSession.setAttribute("principal", principal);		
//			System.out.println("세션 정보가 저장되었습니다.");
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}

	
}
