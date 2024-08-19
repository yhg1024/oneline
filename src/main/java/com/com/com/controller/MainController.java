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
import com.com.com.domain.PageVO;
import com.com.com.service.BoardService;

@Controller
@RequestMapping("/board")
public class MainController {
		
	@RequestMapping("/home")
	public String main() {
		return "home";
	}
	
	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getAllBoards(Model model,
		    @RequestParam(value = "searchType",required = false) String searchType,
		    @RequestParam(value = "keyword",required = false) String keyword,
		    @RequestParam(value = "startDate",required = false) String startDate,
		    @RequestParam(value = "endDate",required = false) String endDate,
		    PageVO vo,
		    @RequestParam(value="nowPage", required=false)String nowPage,
			@RequestParam(value="cntPerPage", required=false)String cntPerPage,
			@RequestParam(value="listCount", required=false)String listCount) throws Exception {
		
		// 전체 목록
		// List<BoardVO> list = boardService.getAllBoards();	
		// model.addAttribute("viewAll", list);		
		
		
		// 페이지네이션
		Integer total = boardService.totalCount();
		
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "10";
		}		

		vo = new PageVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		

		System.out.println("vo = " + vo);
		model.addAttribute("pagination", vo);
		List<BoardVO> list = boardService.list(searchType, keyword, startDate, endDate);

		model.addAttribute("list", list);

		System.out.println("searchType = " + searchType);
		System.out.println("keyword = " + keyword);
		System.out.println("startDate = " + startDate);
		System.out.println("endDate = " + endDate);
		System.out.println("nowPage = " + nowPage);
		System.out.println("cntPerPage = " + cntPerPage);
		System.out.println("list = " + list);	

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
		
		boardService.viewCnt(seq);
		
		return "board/detail";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(int seq, Model model) {
		model.addAttribute("detail", boardService.detail(seq));
		return "/board/write";
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
	
	
	/*--------------------------------------------------------*/
	
	@RequestMapping("/boardList")
	public String boardList(Model model, Map<String, Object> map) {
		List<Map<String, Object>> listMap = boardService.listMap(map);
		model.addAttribute("listData", listMap);
		return "/board/list";
	}
	
	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "board/write";
	}
	
	@RequestMapping("/boardInsert")
	public String boardInsert(Map<String, Object> map) {
		int insert = boardService.boardInsert(map);
		
		if (insert == 0) {
			
		} else {
			
		}
		return "redirect:board/list";
	}
	
	@RequestMapping("/boardDetail")
	public String boardDetail(@RequestParam("seq") int num, Model model) {
		Map<String, Object> detailMap = boardService.boardDetail(num);
		model.addAttribute("detailMap", detailMap);
		return "board/write";
	}
	
}
