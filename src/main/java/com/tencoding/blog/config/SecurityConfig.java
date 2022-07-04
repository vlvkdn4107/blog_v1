package com.tencoding.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tencoding.blog.auth.PrincipalDetailService;

// 필터 만드는 클래스
@Configuration // 빈 등록 (Iod)
@EnableWebSecurity // 시큐리티 필터로 등록이 된다(필터 커스텀 하기위해 재정의하는거다)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증 처리를 미리 체크 하겠다 라고 설정 )(POST) 
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	// WebSecurityConfigurerAdapter 기본 시큐리티에서 커스텀하기위해 상속
	// 1 비밀번호 해쉬 처리
	@Bean // 메모리에 올리기 위한 이노테이션 (Ioc가 된다 필요할때 가져와서 사용하면 된다.)
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	// 2 특정 주소 필터 설정 할 예정
	// 오버라이드 해야한다.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // csrf 란? 사용자는 인증받은 상태인데 악성사이트에서 접속을 해서          // !중요한거는 get방식으로 만들지 마라! POST방식으로 해라!
		.authorizeRequests()
		.antMatchers("/auth/**", "/", "/js/**", "/css/**", "/image/**", "/dummy/**", "/test/**") // 모든 주소가 막혔기때문에 허용 해줄것들을 뚫어준다.// 로그인을 안해도 가능하게 만든다.
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/auth/login_form")// 인증이 안되있으면 로그인 페이지로 돌아가라고 설정 한거다.
		.loginProcessingUrl("/auth/loginProc")
		.defaultSuccessUrl("/");		
	
		// 스프링 시큐리티가 해당 주소로 요청이 오면 가로채서 대신 로그인 처리를 해준다.
		// 단 이 동작을 완료 하기 위해서는 만들어야할 클래스가 있다.
		// 핵심 !! userDetails type에 object를 만들어야 한다.!!
	}
	// 3. 시큐리티가 대신 로그인을 해주는데 여기는 password를 가로채서
	// 해당 패스워드가 무엇으로 해시 처리 되었는지 함수를 알려 줘야한다.
	// 같은 해시로 암호화 해서 DB에 있는 해시값과 비교할수 있다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 1. userDetailService에 들어갈 Object를 만들어 주어야 한다.
		// 2. passwordEncoder 에 우리가 사용하는 해시 함수를 알려 줘야 한다.
		auth.userDetailsService(principalDetailService) // 해당 DetailService에서 알아서 처리를 해줄거다.
		.passwordEncoder(encodePWD());// encodePWD() 위에 만들었음!(해시 알고리즘을 통해서 해당 패스워드가 맞는지 확인을 해준다.
		// 핵심 기능 !!username이 DB에 있는지 확인하고 해쉬코드로 된 패스워드를 확인해준다.

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
