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

	@RequestMapping("/list")
	public String boardList(Model model, PageVO vo, @RequestParam Map<String, Object> map) throws Exception {
	    // 기본값 설정
	    int nowPage = getIntValue(map, "nowPage", 1);
	    int cntPerPage = getIntValue(map, "cntPerPage", 10);

	    // 페이지네이션
	    Integer total = boardService.totalCount(map, vo);
	    vo = new PageVO(total, nowPage, cntPerPage);
	    model.addAttribute("pagination", vo);

	    // map에 페이지 정보 추가
	    map.put("pageVO", vo);

	    List<Map<String, Object>> listMap = boardService.list(map, vo);
	    System.out.println("listMap = " + listMap);
	    model.addAttribute("list", listMap);

	    return "/board/list";
	}
	
	@RequestMapping("/write")
	public String boardInsert(@RequestParam Map<String, Object> map, Model model) throws Exception {
	    if (map != null && map.get("title") != null) { // POST 요청 처리: 글 저장
	        int insert = boardService.insert(map);
	        
	        if (insert == 0) {
	            // 삽입 실패 시 처리 로직 (예: 에러 메시지 추가)
	            model.addAttribute("errorMessage", "글 저장에 실패했습니다.");
	            return "/board/write"; // 실패 시 글쓰기 폼으로 돌아감
	        } else {
	            // 삽입 성공 시 리스트로 리다이렉트
	            return "redirect:/board/list";
	        }
	    }
	    // GET 요청 처리: 글쓰기 폼 보여주기
	    return "/board/write";
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
	
	// 키에 대한 값을 가져오고, 값이 없을 경우 기본값을 반환합니다.
	private int getIntValue(Map<String, Object> map, String key, int defaultValue) {
	    return map.containsKey(key) ? Integer.parseInt(map.get(key).toString()) : defaultValue;
	}
	
	
	/*--------------------------------------------------------*/
	
	
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
	
	
}
