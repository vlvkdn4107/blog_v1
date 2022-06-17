package com.tencoding.blog.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 페이지를 리턴할꺼기 때문에 그냥 컨트롤러
public class BoardController {
	@GetMapping({"","/"})
	public String home() {
		return "home";
	}
}
