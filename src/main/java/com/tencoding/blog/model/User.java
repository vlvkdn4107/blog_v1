package com.tencoding.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
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
public class User {
	@Id // 프라이머리키
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략을 따라가겠다.
	private int id;
	@Column(nullable = false, length = 30) // null이 안되고 최대 30자까지
	private String userName;
	@Column(nullable = false, length = 100)
	private String password;
	@Column(nullable = false, length = 50)
	private String email;

	@ColumnDefault("'user'") // Enum 타입 사용을 권장한다. : admin, user, manager 같은 걸 넣을거다.
	// Enum 타입 쓰는 이유 DOMAIN - 데이터의 범주화(User, ueer 이렇게 잘 못 쓸수가있다) 때문이다.
	private String role;
	@CreationTimestamp // 시간이 자동으로 입력된다.
	private Timestamp createDate;

}
