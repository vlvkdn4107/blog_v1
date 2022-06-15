package com.tencoding.blog.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class BlogControllerTest {

	@GetMapping("/hello")
	public String hello() {
		
		return "<h1>hello spring boot</h1>";
	}
}
