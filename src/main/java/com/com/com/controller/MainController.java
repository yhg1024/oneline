package com.com.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.com.com.service.BoardService;

@Controller
@RequestMapping("/main")
public class MainController {
		
	@RequestMapping("/home")
	public String main() {
		return "home";
	}
	
	@Autowired
	private BoardService boardService;

	@RequestMapping("/boardList")
	public String getAllBoards(Model model) {
	  model.addAttribute("viewAll", boardService.getAllBoards());
	  return "boardList";
	}
	
	@RequestMapping("/boardWrite")
	public String boardWrite(Model model) {
	  model.addAttribute("Model", model);
	  return "boardWrite";
	}
	
}
