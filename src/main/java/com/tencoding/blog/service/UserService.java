package com.tencoding.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	@Transactional
	public int saveUser(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
