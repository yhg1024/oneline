package com.com.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.com.com.domain.BoardVO;
import com.com.com.service.BoardService;
import com.com.com.service.impl.ServiceImpl;

@Controller
@RequestMapping("/board")
public class MainController {
	
		
	@RequestMapping("/home")
	public String main() {
		return "home";
	}
	
	@Autowired
	private BoardService boardService;
	

	@RequestMapping("/list")
	public String getAllBoards(Model model) {
	  model.addAttribute("viewAll", boardService.getAllBoards());
	  return "board/list";
	}
	
	ServiceImpl mBoardService;
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String boardWrite() {
		
	  return "board/write";
	}
	
	@RequestMapping("/writeProc")
	public String BoardWrite(BoardVO vo) throws Exception {
		boardService.insertBoard(vo);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/detail")
	public String BoardDetail(Model model, int seq) {
		model.addAttribute("detail", boardService.detail(seq));
		return "board/detail";
	}
	
	public String BoardUpdate(BoardVO vo) throws Exception {
		boardService.insertBoard(vo);
		return "redirect:/board/list";
	}
	
}
