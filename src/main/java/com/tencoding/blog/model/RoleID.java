package com.tencoding.blog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

// @Embeddable 쓸때는 무조건 @NoArgsConstructor(기본 생성자) 를 써줘야 한다 
@Embeddable
@NoArgsConstructor
public class RoleID implements Serializable{

	@Column(length = 225)
	private String title;
	
	@Column(length = 20)
	private String actorName;
	
}
