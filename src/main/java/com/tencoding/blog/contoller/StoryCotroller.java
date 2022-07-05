package com.tencoding.blog.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.RequestFileDto;
import com.tencoding.blog.service.StoryService;


@Controller
@RequestMapping("/story")
public class StoryCotroller {

		@Autowired
		private StoryService storyService;
	
	
		@GetMapping("/home")
		public String storyHome() {
			
			return "/story/home";
		}
		@GetMapping("/upload")
		public String storyUpload() {
			
			return "/story/upload";
		}
		@PostMapping("/image/upload") // 마인타입인 멀티 타입으로 넘어온다.
//		public String storyImageUpload(MultipartFile file, String storyText) {
		public String storyImageUpload(RequestFileDto fileDto, @AuthenticationPrincipal PrincipalDetail principalDetail) { // 파일 타입을 받을려면 // .jsp 파일에 name 값과 같아야한다.
			storyService.ImageUpload(fileDto,principalDetail.getUser());
			
			// 1byte -> 1000byte(1KB 키로바이트) -> 1,000,000btye(1MB 메가바이트) -> 
			// 1,000,000,000btye(1GB 기가바이트) -> 1,000,000,000,000btye(1TB 테라바이트)
			
			// 1bit = 2진수 (0 과 1)
			// 1byte = 8bit
			// 1KB = 2의 10승 바이트 , 2를 10번 곱한것 = 1024byte
			// 1byte -> 1024byte(KB) -> 1024KB -> ....
			
			// 1KB = 1024byte
			// 컴퓨터는 왜 1byte의 1000배가 아니라 1024배 일까?
			// 컴퓨터가 1000배 보다는 1024배를 훨씬 빨리 계산하기 떄문이다???? 왜?
			// 좀 더 빠른 속도를 얻기 위해서 1024배로 약속한 것이다
			
			// 컴퓨터는 2진수로 계산 하는것이 가장 편하고 빠르기 때문이다.
			// 숫자를 2진수 단위로 관리 한다.
			// 그래서 컴퓨터는 2, 4, 8, 32, 64, 128 , 256, 512, 1024 와 같이
			// 2의 제곱으로 된 단위를 사용한다.
			
			
			return "/story/home";
		}
		
}
