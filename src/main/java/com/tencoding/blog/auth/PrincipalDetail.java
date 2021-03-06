package com.tencoding.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tencoding.blog.model.User;

import lombok.Data;

@Data
public class PrincipalDetail implements UserDetails{

	/**
	 *  모든 Class는 UID(고유 식별자)를 가지고 있다.
	 *  Class의 내용이 변경되면 UID값 역시 같이 변경 됩니다.
	 *  직렬화 하여 통신하고. UID값으로 통신한게 정상인지 확인을 하는데 
	 *  그 값이 바뀌게 되면 다른 class로 인식을 해버리게 된다.
	 *  이를 방지 하기 위해서 UID으로 미리 명시를 해주는 부분이
	 *  serialVersionUID 이 녀석 이다.
	 *  반드시 해야하는 필수조건은 아니지만 권장한다.
	 */
	private static final long serialVersionUID = 1L; // 직렬화 
	
	private User user; // 콤포지션 관계

	public PrincipalDetail(User user) {
		this.user = user;
	}
	

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	/*
	 * 계정이 만료되지 않았는지 리턴 한다.
	 * (true) 만료 안된상태
	 * (false) 만료 된 상태
	 * */
	@Override
	public boolean isAccountNonExpired() {
		// 트루로 해줘야 로그인 처리가된다.
		return true;
	}

	/*
	 * 계정이 잠겼는지 안잠겼는지 여부 확인
	 * (true) 사용가능
	 * (false) 사용불가
	 * */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	
	/*
	 * 비밀번호 만료 여부를 알려준다.
	 * (true) 사용가능
	 * (false) 사용 불가
	 * */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*
	 * 계정 활성화 여부
	 *(true) 사용가능
	 *(false) 사용 불가
	 * */
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	/*
	 * 계정의 권환을 반환한다.
	 * */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Collection 은 리스트 계열의 최상위 객체임
		Collection<GrantedAuthority> collectors = new ArrayList<GrantedAuthority>();
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				// "ROLE_" 스프링 시큐리티 규칙(꼭 넣기)
//				// "ROLE_USER", "ROLE_ADMIN"
//				return "ROLE_" + user.getRole();
//			}
//		});
		// 람다 식으로
		collectors.add(() -> {
			return "ROLE_" + user.getRole();
		});
		return collectors;
	}
	
}
