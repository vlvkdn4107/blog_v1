 package com.tencoding.blog.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tencoding.blog.service.BoardService;

@Controller // 페이지를 리턴할꺼기 때문에 그냥 컨트롤러
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	@GetMapping({"","/"})
	public String home() {
		return "home";
	}
	
	
//	@GetMapping("/boardsave_form")
//	public String boardsave() {
//		return "user/boardsave_form";
//	}
//	
//	
//	
//	@PostMapping("/boardsave")
//	@ResponseBody
//	public String boardsave(@RequestBody BoardSaveRequestDto dto) {
//		boardService.boardSave(dto);
//		return "ok";
//	}
}
