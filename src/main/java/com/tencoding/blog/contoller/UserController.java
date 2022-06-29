package com.tencoding.blog.contoller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencoding.blog.dto.KakaoProfile;
import com.tencoding.blog.dto.KakaoProfile.KakaoAccount;
import com.tencoding.blog.dto.OAuthToken;
import com.tencoding.blog.model.User;
import com.tencoding.blog.service.UserService;

@Controller
public class UserController {
	
	@Value("${tenco.key}")
	private String tencoKey;
	
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private UserService service;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
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
		
		@GetMapping("/auth/kakao/callback")
		public String kakaoCallback(@RequestParam String code) {
			//Retrofit2
			//HTTPSURLConnect
			//OKHttp
			// 스프링에서는  RestTemplate  를 많이 쓴다
			RestTemplate rt = new RestTemplate();
			// http 메세지 ->  POST
			// 시작줄
			// http header
			// http body
			
			//header 생성
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			// body 생성
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			// 어떤 값을 넣어야 할까??????? (문서 확인)
			params.add("grant_type", "authorization_code");
			params.add("client_id", "e328d258617af0d92f0569239ac4463d");
			params.add("redirect_uri", "http://localhost:9090/auth/kakao/callback");
			params.add("code", code);
			
			// 헤더와 바디를 하나의 오브젝트로 담아야한다.
			HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
			 
			// Http 요청 -> POST 방식 -> 응답을 받아야한다.
			ResponseEntity<String> responseEntity = rt.exchange("https://kauth.kakao.com/oauth/token",
					HttpMethod.POST,
					kakaoTokenRequest,
					String.class);
			
			// reponseEntity -> Object로 반환 (Gson, Json Simple, ObjectMapper)
			// 파싱
			OAuthToken authToken = null;
			ObjectMapper mapper = new ObjectMapper();
			// String --> Object로 변환 할려면 클래스를 생성해야한다.
			try {
				authToken = mapper.readValue(responseEntity.getBody(), OAuthToken.class);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			// 엑세스 토큰 사용
			RestTemplate rt2 = new RestTemplate();
			HttpHeaders headers2 = new HttpHeaders();
			// 주의 Bearer 다음에 무조건 한칸 띄우기!!!!!!!!!!!!!!!!!!!!
			headers2.add("Authorization", "Bearer " + authToken.getAccessToken());
			headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			// 바디 
			HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);
			ResponseEntity<KakaoProfile> kakaoProfileResponse = rt2.exchange("https://kapi.kakao.com/v2/user/me", 
					HttpMethod.POST, 
					kakaoProfileRequest, 
					KakaoProfile.class);
		
			// 단 위에 했던 방식으로 전환 (kakaoProfile.class)
			// 소셜 로그인 처리 --?
			// 사용자가 로그인 했을 경우 최초 사용자라면 -> 회원가입 처리 한다.
			// -> 한번 이라도 가입 진행이 된 사용자면 로그인 처리를 해주면 된다.
			// 만약 회원 가입시 필요한 정보가 더 있어야 한다면 추가로 사용자한테 정보를 받아서 가입 진행처리를 해야한다.
			
			KakaoAccount account = kakaoProfileResponse.getBody().getKakaoAccount();
			System.out.println("카카오 ID : " + kakaoProfileResponse.getBody().getId());
			System.out.println("카카오 Email : " + account.getEmail());
			
			System.out.println("블로그에서 사용 될 username : " + account.getEmail() + "_" + kakaoProfileResponse.getBody().getId());
			System.out.println("블로그에서 사용 될 이메일 : " + account.getEmail());
			
			User kakaoUser = User.builder()
					.username(account.getEmail() + "_" + kakaoProfileResponse.getBody().getId())
					.password(tencoKey)
					.email(account.getEmail())
					.Oauth("kakao")
					.build();
			System.out.println(kakaoUser + " kakaoUser");
			// UserService 호출해서 저장 진행
			User originUser =  service.searchUser(kakaoUser.getUsername());
			if(originUser.getUsername() == null) { // null 이면 신규 회원이라는거임
				System.out.println("신규가 아니기 떄문에 회원가입 진행");
				service.saveUser(kakaoUser);
			}
			
			// 자동 로그인 처리 -> 시큐리티세션에다가 Authentication(강제 저장) 하면된다.
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), tencoKey));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			return "redirect:/";
		}		
		
		
}
