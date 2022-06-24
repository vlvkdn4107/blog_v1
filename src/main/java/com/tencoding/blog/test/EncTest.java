package com.tencoding.blog.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {
	
	@Test // 코드 테스트 하게 해주는 어노테이면 Junit 5선택
	public void hashEncode() {
		String encPassword = new BCryptPasswordEncoder().encode("123");
		System.out.println(encPassword + " : 헤쉬값");
		
		// $2a$10$WafzP36pjNyq4TH2iJxtxupCCNlrCr7Bg.275ctbk/YcjSHka8XMK
		// $2a$10$u2y.zptW4jEEfop5IAFUkODRUxKyuEw.2Wkfx7BLxsJ20BWIEnVCW
	}
}
