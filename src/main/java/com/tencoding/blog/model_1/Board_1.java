package com.tencoding.blog.model_1;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

public class Board_1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 100)
	private String title;
	@Lob
	private String content;
	@ColumnDefault("0") // int 타입은 "" String 은 "' '"
	private int count;
	@ManyToOne(fetch = FetchType.EAGER) // ManyToOne 연관관계를 만드는것 // fetch.eager은 board selete 할때 한번에 데이터를 들고오라는 것
	// Many == board  One == user //즉 여러개의 게시글은 하나의 user가 달수있다.
	@JoinColumn(name = "userId_1") // join의 컬럼명은 userId_1 이다.
	private User_1 userId;
	@CreationTimestamp
	private Timestamp createDate;
}
