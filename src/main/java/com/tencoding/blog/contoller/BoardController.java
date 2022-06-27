 package com.tencoding.blog.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tencoding.blog.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // 페이지를 리턴할꺼기 때문에 그냥 컨트롤러
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	@GetMapping({"","/"})
	public String home(@PageableDefault(size = 3, sort ="id", direction = Direction.DESC)Pageable pageable , Model model) {
		// 일반적으로 이렇게 pageable 자체를 던진다.
		model.addAttribute("pageable", boardService.getBoardList(pageable));
		return "index";
	}
	
	
	@GetMapping("/board/save_form")
	public String saveForm() {
		log.info("saveForm 메서드 호출");
		return "/board/save_form";
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		// todo서비스 메서드 만들기 (서비스클래스에서)
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/detail";
	}
	
}
