package com.tencoding.blog.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	// UserRepository 메모리에 올라가있는 상태.
	// 그럼 어떻게 가져 오나요? DI
	@Autowired // 자동으로 new 때려 준다. (레퍼런스변수가 담겨짐)
	private UserRepository userRepository;
	
	// REST POST
	@PostMapping("/dummy/join")
	public String join(@RequestBody User user) {
		System.out.println("========================");
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		System.out.println("========================");
		
		System.out.println(user.getId());
		System.out.println(user.getCreateDate());
		System.out.println(user.getRole()); // null 이지만 default 값이 안먹힌다!
		
		//해결
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		
		return "회원가입 완료 되었습니다.";
	}

}
