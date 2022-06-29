package com.tencoding.blog.contoller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencoding.blog.dto.OAuthResponseDto;
import com.tencoding.blog.model.User;
import com.tencoding.blog.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private UserService userservice;
	
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

		
		@PostMapping("/auth/joinProc")
		// 기본 데이터 파싱 전략 key=value
		// application/X-www-from-urlencoded;charset=UTF-8 // key = value
		public String save(User user){ // JSON으로  던지기 위해서는 @RequestBody를 쓰는데 아니기 때문에 안쓴다.
			int result = userservice.saveUser(user);
			return "redirect:/";
		}
		
		@GetMapping("/auth/kakao/callback")
		@ResponseBody
		public String kakaoCallback(@RequestParam String code) {
			
			RestTemplate rt = new RestTemplate();
			
			// 헤더 생성
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			// 바디
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authorization_code");
			params.add("client_id", "e328d258617af0d92f0569239ac4463d");
			params.add("redirect_uri", "http://localhost:9090/auth/kakao/callback");
			params.add("code", code);
			
			// 헤더와 바디를 담는 오브젝트
			HttpEntity<MultiValueMap<String, String>> kakaoTokenReq = new HttpEntity<>(headers, params);
			
			// Http 요청
			ResponseEntity<String> repEntity = rt.exchange("https://kauth.kakao.com/oauth/token",
					HttpMethod.POST,
					kakaoTokenReq,
					String.class); 
			
			OAuthResponseDto authResponseDto = null;
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				authResponseDto = mapper.readValue(repEntity.getBody(), OAuthResponseDto.class);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "repEntity : " + authResponseDto;
		}
		
	
		
}
