package com.tencoding.blog.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = repository.findByUsername(username)
				.orElseThrow(() -> {
					return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
				});
		return new PrincipalDetail(principal); // 리턴을 하게 되면 시큐리티 세션에 유저 정보가 저장이된다.
	}
	
}
