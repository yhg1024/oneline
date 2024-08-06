package com.com.com.controller;

import java.util.List;

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
	private ServiceImpl mBoardService;

	@RequestMapping("/list")
	public String getAllBoards(Model model) {
		List<BoardVO> list = boardService.getAllBoards();
		
		// List<Map<String, Object>> listMap = boardService.getAllBoards();
		
	  model.addAttribute("viewAll", list);
	  return "/board/list";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String boardWrite() {
		
	  return "/board/write";
	}
	
	@RequestMapping(value="/write")
	public String boardWrite(BoardVO vo) throws Exception {
		boardService.insertBoard(vo);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/detail")
	public String boardDetail(Model model, int seq) {
		model.addAttribute("detail", boardService.detail(seq));
		return "board/detail";
	}
	
	@RequestMapping(value="/update?seq=${seq}", method=RequestMethod.GET)
	public String update(Model model, int seq) {
		model.addAttribute("detail",boardService.detail(seq));
		return "/board/update";
	}
	
	@RequestMapping("/update")
	public String update(BoardVO vo) {
		boardService.update(vo);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/delete")
	public String delete(int seq) {
		boardService.delete(seq);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/deleteList")
	public String deleteList(Integer[] seq) {
		
		
		return "redirect:/board/list";
	}
	
}
