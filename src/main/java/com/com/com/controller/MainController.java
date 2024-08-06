package com.com.com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		List<BoardVO> list = boardService.getAllBoards();
		
		// List<Map<String, Object>> listMap = boardService.getAllBoards();
		
	  model.addAttribute("viewAll", list);
	  return "/board/list";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		
	  return "/board/write";
	}
	
	@RequestMapping(value="/write")
	public String write(BoardVO vo) throws Exception {
		boardService.insert(vo);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/detail")
	public String detail(Model model, int seq) {
		model.addAttribute("detail", boardService.detail(seq));
		return "board/detail";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update() {
		return "/board/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
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
	public String deleteList(Integer[] list) {	
		boardService.delete(list);
		return "redirect:/board/list";
	}
	
	
	/*--------------------------------------------------------*/
	
	@RequestMapping("boardList")
	public String boardList(Model model, Map<String, Object> map) {
		List<Map<String, Object>> listMap = boardService.listMap(map);
		model.addAttribute("listData", listMap);
		return "/board/list";
	}
	
	@RequestMapping("boardWrite")
	public String boardWrite() {
		return "board/write";
	}
	
	@RequestMapping("boardInsert")
	public String boardInsert(Map<String, Object> map) {
		int insert = boardService.boardInsert(map);
		
		if (insert == 0) {
			
		} else {
			
		}
		return "redirect:board/list";
	}
	
	@RequestMapping("boardDetail")
	public String boardDetail(@RequestParam("seq") int num, Model model) {
		Map<String, Object> detailMap = boardService.boardDetail(num);
		model.addAttribute("detailMap", detailMap);
		return "board/write";
	}
	
}
