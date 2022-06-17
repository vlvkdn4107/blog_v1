package com.tencoding.blog.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
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
}
