package com.tencoding.blog.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	// http://localhost:9090/blog/temp/home
		@GetMapping("/temp/home")
			public String temphome() {
			// 파일 리턴 경로 : src/main/resource/static
			// 리턴명 : /home.html
				System.out.println("temphome");
				return "/home.html";
			
		    }
}
