package com.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.model.Board;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void write(Board board, User user) { // board에서 넘어오는 값은 titls,content가 넘어온다.
		board.setCount(0);
		board.setUserId(user);
		boardRepository.save(board);
	
	}
	
	@Transactional
	public Page<Board> getBoardList(Pageable pageable){
		return boardRepository.findAll(pageable); // DB에 있는걸 다 긁어서 리턴해줌
	}
	
	@Transactional
	public Board boardDetail(int boardId) {
		return boardRepository.findById(boardId).orElseThrow(() ->{
			return new IllegalArgumentException("해당 글은 찾을 수 없습니다."); // 반드시 예외 처리해주자!
		});
	}
	
	@Transactional
	public void deleteById(int id) {
		boardRepository.deleteById(id);
	}
}
