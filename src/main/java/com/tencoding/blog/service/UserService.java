package com.tencoding.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.UserRepository;

@Service // ioc 등록
public class UserService {
	/*
	 *  서비스가 필요한 이유
	 *  현실 모델링 : 레스토랑  (주문 : 웨이터(서버) -> 주방장)
	 *  
	 *  트렌젝션(작업하는 가장작은 단위) 관리
	 *  송금 : 홍길동 , 이순신
	 *  홍길동이 원래 계좌에 10만원  이순신은 0원 --> 홍길동 계좌에 접근(select)해서 확인후 --> 이슌산(5만원) (insert)
	 *  작은 기능들을 묶어서 하나의 기능으로 만드는걸 service라고 한다.
	 *  
	 *  하나의 기능 + 하나의 기능 을 묶어서 단위의 기능을 만들어 내기 위해 필요하다.
	 * 
	 *  물론 하나의 기능도 서비스가 될수있다.
	 */
	// DI 의존 주입!
	@Autowired // 자동으로 초기화
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public int saveUser(User user) {
		// select
		// update
		// insert
		try {
			String rawPassword = user.getPassword();
			
			// SHA-512 로 암호화 한거다.
			String encPassword = encoder.encode(rawPassword);
			
			user.setPassword(encPassword);
			user.setRole(RoleType.USER);
			userRepository.save(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	@Transactional
	public void updateUser(User user) {
		User userEntity = userRepository.findById(user.getId())
				.orElseThrow(() -> {
					return new IllegalArgumentException("회원 정보가 없습니다.");
				});
		// 해시 암호화 처리
		String rawPassword = user.getPassword();
		String hashPassword = encoder.encode(rawPassword);
		userEntity.setPassword(hashPassword);
		userEntity.setEmail(user.getEmail());
		
	}
	
	@Transactional(readOnly = true)
	public User searchUser(String username) {
		User userEntity = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
		return userEntity;
	}
	
	
//	@Transactional(readOnly = true)
//	public User login(User user) {
//		// 서비느느 레파지토리한테 select 시켜야한다.
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
	

}
