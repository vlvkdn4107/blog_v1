package com.tencoding.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.model.Board;


public interface BoardRepository extends JpaRepository<Board, Integer>{

	
	
	// 함수로 쿼리 만들기(검색 기능)
	// select * from board where title like '%title%';
	Page<Board> findByTitleContaining(String title, Pageable pageable);
	
	
}
