package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.model.User;

// IoC 처리는 자동으로 되기때문에 안해줘도 된다.
// Bean 으로 등록될수 있나요? --> 스프링에서 Ioc 에서 객치를 가지고 있나요? 와 같은 말이다.
// DAO
public interface UserRepository extends JpaRepository<User, Integer>{
	
	// insert
	// select
	// update
	// delete
}
