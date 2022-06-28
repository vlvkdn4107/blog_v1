package com.tencoding.blog.contoller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.blog.model.User;
import com.tencoding.blog.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private UserService service;
	
	// http://localhost:9090/blog/user/login_form
	@GetMapping("/auth/login_form")
	public String loginForm() {
		return "user/login_form";
	}
	
	// http://localhost:9090/blog/user/join_form
		@GetMapping("/auth/join_form")
		public String joinForm() {
			return "user/join_form";
		}
		
		@GetMapping("/logout") 
		public String logout() {
			// 세션정보를 제거 (로그아웃 처리)
			httpSession.invalidate();
			return "redirect:/";
		}
		@GetMapping("/user/update_form")
		public String updateForm() {
			return "user/update_form";
		}
		
		@PostMapping("/auth/joinProc") // 페이지를 리턴해주는 UserController로 옮겼음! (원래는 API에있었음)
		// 기본 데이터 파싱 전략 key=value
		// application/X-www-from-urlencoded;charset=UTF-8 // key = value
		public String save(User user){ // JSON으로  던지기 위해서는 @RequestBody를 쓰는데 아니기 때문에 안쓴다.
			int result = service.saveUser(user);
			return "redirect:/"; // redirect로 페이지 이동 처리함
		}
}
