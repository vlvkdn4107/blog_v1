package com.tencoding.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 자동으로 MySQL 에 테이블을 생성한다.
// 테이블이 이미 있으면 자동으로 삭제했다가 다시 생성해준다.
//@DynamicInsert
public class User {
	@Id // 프라이머리키
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략을 따라가겠다.
	private int id;
	@Column(nullable = false, length = 100, unique = true) // null이 안되고 최대 30자까지 unique 보존성 (같은 이름으로 회원가입 안됌!)
	private String username;
	@Column(nullable = false, length = 100)
	private String password;
	@Column(nullable = false, length = 50)
	private String email;

//	@ColumnDefault("'user'") // null이면 user을 반환하라// Enum 타입 사용을 권장한다. : admin, user, manager 같은 걸 넣을거다.
	// Enum 타입 쓰는 이유 DOMAIN - 데이터의 범주화(User, ueer 이렇게 잘 못 쓸수가있다) 때문이다.
	@Enumerated(EnumType.STRING)// enum 클래스로 만들었기 때문에 알려줘야한다!
	private RoleType role;
	
	private String Oauth; // kakao , google, naver ... 
	
	@CreationTimestamp // 시간이 자동으로 입력된다.
	private Timestamp createDate;

}
