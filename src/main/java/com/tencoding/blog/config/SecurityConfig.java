package com.tencoding.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
	
	// 2 특정 주소 필터 설정 할 예정
	// 오버라이드 해야한다.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/auth/**", "/", "/js/**", "/css/**", "/image/**") // 모든 주소가 막혔기때문에 허용 해줄것들을 뚫어준다.
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/auth/login_form");// 인증이 안되있으면 로그인 페이지로 돌아가라고 설정 한거다.
	
	}
}
