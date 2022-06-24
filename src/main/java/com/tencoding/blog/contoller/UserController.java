package com.tencoding.blog.contoller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@Autowired
	private HttpSession httpSession;
	
	
	// http://localhost:9090/blog/user/login_form
	@GetMapping("/login_form")
	public String loginForm() {
		return "user/login_form";
	}
	
	// http://localhost:9090/blog/user/join_form
		@GetMapping("/join_form")
		public String joinForm() {
			return "user/join_form";
		}
		
		@GetMapping("/logout")
		public String logout() {
			// 세션정보를 제거 (로그아웃 처리)
			httpSession.invalidate();
			return "redirect:/";
		}
}
