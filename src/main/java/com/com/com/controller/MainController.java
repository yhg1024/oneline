package com.com.com.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

	@RequestMapping(value = "/list")
	public String getAllBoards(Model model,
//		    @RequestParam(value = "searchType",required = false) String searchType,
//		    @RequestParam(value = "keyword",required = false) String keyword,
//		    @RequestParam(value = "startDate",required = false) String startDate,
//		    @RequestParam(value = "endDate",required = false) String endDate,
		    PageVO vo,
//		    @RequestParam(value="nowPage", required=false)Integer nowPage,
//			@RequestParam(value="cntPerPage", required=false)Integer cntPerPage
		    @RequestParam Map<String, Object> map
			) throws Exception {
		
		// 전체 목록
		// List<BoardVO> list = boardService.getAllBoards();	
		// model.addAttribute("viewAll", list);		
		
		int nowPage = map.get("nowPage") == null ? 1 : Integer.parseInt(map.get("nowPage").toString()) ;
		int cntPerPage = map.get("cntPerPage") == null ? 10 : Integer.parseInt(map.get("cntPerPage").toString()) ;
		String searchType = map.get("searchType") == null ? "" : map.get("searchType").toString();
		String keyword = map.get("keyword") == null ? "" : map.get("keyword").toString();
		String startDate = map.get("startDate") == null ? "" : map.get("startDate").toString();
		String endDate = map.get("endDate") == null ? "" : map.get("endDate").toString();
		 
		// 페이지네이션
		Integer total = boardService.totalCount(searchType, keyword, startDate, endDate, vo);
		
		
		
//		if (nowPage == null && cntPerPage == null) {
//			nowPage = "1";
//			cntPerPage = "10";
//		} else if (nowPage == null) {
//			nowPage = "1";
//		} else if (cntPerPage == null) {
//			cntPerPage = "10";
//		}		

		vo = new PageVO(total, nowPage, cntPerPage);
		

		System.out.println("vo = " + vo);
		model.addAttribute("pagination", vo);
		
		List<BoardVO> list = boardService.list(searchType, keyword, startDate, endDate, vo);
		
		model.addAttribute("list", list);
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
	
	@RequestMapping("/boardDelete")
	public String boardDelete(Integer[] chk) {
		List<Integer> list = Arrays.asList(chk);
		List<Integer> listA = new ArrayList<Integer>();
		
		for (int i = 0; i < chk.length; i++) {
			listA.add(chk[i]);
		}
		
		int delete = boardService.delete(chk);
		
		return "redirect:list";
	}
	
	@RequestMapping("/boardDetail")
	public String boardDetail(@RequestParam("seq") int num, Model model) {
		Map<String, Object> detailMap = boardService.boardDetail(num);
		model.addAttribute("detailMap", detailMap);
		return "board/write";
	}
	
}
