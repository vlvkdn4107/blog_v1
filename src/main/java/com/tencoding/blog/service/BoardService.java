package com.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.model.Board;
import com.tencoding.blog.model.Reply;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.BoardRepository;
import com.tencoding.blog.repository.ReplyRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
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
	
	@Transactional
	public void modifyBoard(int id, Board board) {
		// 기존에 있던 title, content 불러오기
		Board boardEntity = boardRepository.findById(id).orElseThrow(() ->{
			return new IllegalArgumentException("해당 글은 찾을 수 없습니다."); // 반드시 예외 처리해주자!
		}); 
		// 불러온 title, content를 수정하기
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		
		// 더티체킹 하기
	}
	@Transactional
	public Reply writeReply(User user, int baordId, Reply requestreply) { // 매개변수가 많으니깐 다음에는 DTO를 활용하자
		Board boardEntity =  boardRepository.findById(baordId).orElseThrow(()->{
			return new IllegalArgumentException("댓글 쓰기 실패! : 해당 게시글이 존재하지 않습니다.");
		});
		
		requestreply.setUser(user);
		requestreply.setBoard(boardEntity);	
		Reply replyEntity = replyRepository.save(requestreply); // .save는 값을 리턴해준다.
//		System.out.println("데이터 확인 댓글 : " + replyEntity); // << 이 sysout을 하면 무한참조 오류가 발생한다.
		return replyEntity;
	}
	
	@Transactional
	public void deleteReplyById(int replyId) {
		replyRepository.deleteById(replyId);
	}
	@Transactional
	public Page<Board> searchBoardByTitle(String title, Pageable pageable) {
		
		return boardRepository.findByTitleContaining(title, pageable);
	}
	
}
