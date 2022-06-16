package com.tencoding.blog.model_1;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

public class Reply_1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne // Many == Reply  One == board //즉 여러개의 댓글은 하나의 게시글에 달수있다.
	@JoinColumn(name = "boardId")
	private Board_1 board_1;
	
	@ManyToOne // Many == Reply  One == user //즉 여러개의 댓글은 하나의 user가 달수있다.
	@JoinColumn(name = "userId")
	private User_1 user_1;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
