package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.model.User;
import com.tencoding.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userservice;
	@Autowired
	private AuthenticationManager authenticationManager; // 미리 시큐리티에 올려놔야한다.@Bean 등록 (주의!)

	@PostMapping("/auth/joinProc")
	// 기본 데이터 파싱 전략 key=value
	// application/X-www-from-urlencoded;charset=UTF-8 // key = value
	public ResponseDto<Integer> save(User user){ // JSON으로  던지기 위해서는 @RequestBody를 쓰는데 아니기 때문에 안쓴다.
		int result = userservice.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		userservice.updateUser(user);
		// 강제로 Authentication 객체를 만들고 SecurityContext안에 집어 넣으면 된다.
		// 1. Authentication 객체 생성
		// 2. AuthenticationManager 메모리에 올려서 authenticate 메서드를 사용해서 Authentication 객체를 저장한다.
		// 3. 세션 - SecurityContextHolder.getContext().setAuthentication() Authentication 을 넣어주면 된다.
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication); 
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
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
