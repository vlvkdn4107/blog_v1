package com.tencoding.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 200)
	private String content;

	// 여러개의 댓글은 하나의 게시글에 존재할수있다.
	@ManyToOne // Many == Reply, One == Board
	@JoinColumn(name = "boardId")
	@JsonIgnoreProperties({"board","reply","userId"})
	private Board board;

	//여러개의 댓글은 하나의 user가 달수있다.
	@ManyToOne // Many == Reply, One == User
	@JoinColumn(name = "userId") // userId이름으로 join해라 (즉 테이블의 컬럼명이 userId)
	@JsonIgnoreProperties({"password", "role", "oauth","email"})
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
