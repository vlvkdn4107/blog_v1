package com.tencoding.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 100)
	private String title;
	@Lob // 대용량 데이터를 뜻한다.
	private String content;
	@ColumnDefault("0") // int 형이다. //String을 쓰고 싶으면 "'안녕'" << 이렇게 써야한다.
	private int count; // 게시글 조회수

	
	// 여러개의 게시글은 하나의 User를 가진다. 반대로 하나의 게시글은 여러명의 User를 가질수 없다.
	@ManyToOne(fetch = FetchType.EAGER) // 연관관계 
	// Many == board, One == User  
	// fetchType.EAGER는 Board select 할때 한번에 데이터를 가져와
	@JoinColumn(name = "userId") // 조인해서 들고와라 이름은 userId로 즉 join 컴럼명(as)은 userId이다
	private User userId;
	
	// 댓글 정보 
	// 하나에 게시글에 여러개의 댓글이 있을수 있다.
	// mappedBy = "board" board는 reply 테이블에 필드이름이다.
	// 하지만 mappedBy는 연관 관계에 주인이 아니다. 즉,FK가 아니다
	// DB 에 컬럼을 만들지 마시오.
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"board"}) // Reply안에 있는 board getter를 무시 해라 (호출x)
	private List<Reply> reply;
	
	
	
	@CreationTimestamp
	private Timestamp createDate;
}
