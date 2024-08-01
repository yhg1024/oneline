package com.com.com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.com.com.domain.BoardVO;
import com.com.com.service.BoardService;
import com.com.com.service.impl.ServiceImpl;

@Controller
public class MainController {
	
		
	@RequestMapping("/home")
	public String main() {
		return "home";
	}
	
	@Autowired
	private BoardService boardService;
	

	@RequestMapping("/board/list")
	public String getAllBoards(Model model) {
	  model.addAttribute("viewAll", boardService.getAllBoards());
	  return "boardList";
	}
	
	ServiceImpl mBoardService;
	
	@RequestMapping("/board/write")
	public String boardWrite() {
		
	  return "boardWrite";
	}
	
	@RequestMapping("/boardWriteProc")
	public String BoardWrite(BoardVO vo) throws Exception {
		System.out.println(vo);
		boardService.insertBoard(vo);
		return "redirect:/board/list";
	}
	
}
