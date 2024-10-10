package com.com.com.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nexacro.java.xapi.data.DataSet;
import com.nexacro.java.xapi.data.DataTypes;
import com.nexacro.java.xapi.data.PlatformData;
import com.nexacro.java.xapi.data.VariableList;
import com.nexacro.java.xapi.tx.HttpPlatformRequest;
import com.nexacro.java.xapi.tx.HttpPlatformResponse;
import com.nexacro.java.xapi.tx.PlatformException;

@Controller
public class BoardController {
	
	@Inject
	public BoardServiceInter service;
	
	@Inject
	public SqlSessionTemplate sqlSession;

	@RequestMapping("/list")
	public String list(@RequestParam Map<String, Object> map, Model model) {
		// 기본값 설정
	    int nowPage = getIntValue(map, "nowPage", 1);
	    int listSize = getIntValue(map, "listSize", 10);
		
		// 페이지네이션
		int total = service.totalCount(map);
		
		PageVO page = new PageVO(total, nowPage, listSize);
		model.addAttribute("pagination", page);		
		
		 // map에 페이지 정보 추가
		map.put("start", page.getStart());
		map.put("end", page.getEnd());
		
		model.addAttribute("list", service.list(map));
		
		return "board/list";
	}
	
//	@RequestMapping("/insert")
//	public String insert(@RequestParam Map<String, Object> map, Model model, @RequestParam("uploadFile") MultipartFile uploadFile) {
//		if (map != null && map.get("boardSubject") != null) {
//			System.out.println(map);
//			// POST 요청 처리: 글 저장
//			int insert = service.insert(map);
//			
//			if(insert == 0) {	
//				// 삽입 실패 시 처리 로직 (예: 에러 메시지 추가)
//	            model.addAttribute("errorMessage", "글 저장에 실패했습니다.");
//	            return "/board/write"; // 실패 시 글쓰기 폼으로 돌아감
//			} else {	
//				// 삽입 성공 시 리스트로 리다이렉트
//	            return "redirect:/list";
//			}
//		}
//		// GET 요청 처리: 글쓰기 폼 보여주기
//	    return "/board/write";
//	}
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestParam Map<String, Object> map, Model model, MultipartHttpServletRequest uploadFile) throws IllegalStateException, IOException {
		System.out.println("map = " + map);
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		int listSeq = sqlSession.selectOne("mapper.listSeq"); //  게시글 최신 번호를 가져온다.
		int insert = service.insert(map);
		
		if(insert == 0) {	
			// 삽입 실패 시 처리 로직 (예: 에러 메시지 추가)
            model.addAttribute("errorMessage", "글 저장에 실패했습니다.");
            return "/board/write"; // 실패 시 글쓰기 폼으로 돌아감
		} else {
			// 1. 파일 저장 경로 설정 : 실제 서비스되는 위치(프로젝트 외부에 저장)
			String uploadPath = "C:/Users/dev/Desktop/img/";
			// 마지막에 / 있어야한다.
			
			Iterator<String> itr = uploadFile.getFileNames(); // 단일 여러개
			
			while (itr.hasNext()) {
			    MultipartFile mFile = uploadFile.getFile(itr.next());
			    
			    // 2. 원본 파일 이름 알아오기
			    String originalFileName = mFile.getOriginalFilename();
			    
			    // 3. 파일 이름이 중복되지 않도록 파일 이름 변경 : 서버에 저장할 이름
			    // UUID 클래스 사용
			    UUID uuid = UUID.randomUUID();
			    String saveFileName = uuid.toString() + "_" + originalFileName;
			    
			    // 4. 파일 생성
			    File newFile = new File(uploadPath + saveFileName);
			    
			    if(mFile.getSize() > 0) { // 파일 크기(용량)가 0 이상인것(진짜 있는 파일인지)
			    	
			    	// 5. 서버로 전송
			    	mFile.transferTo(newFile);
			    	
			    }
			    
			    fileMap.put("originalFileName", originalFileName);
			    fileMap.put("saveFileName", saveFileName);
			    fileMap.put("uploadPath", uploadPath);
			    fileMap.put("seq", listSeq);
			    
			    service.file(fileMap);
			}
			
			// 삽입 성공 시 리스트로 리다이렉트
            return "redirect:/list";
		}
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		// GET 요청 처리: 글쓰기 폼 보여주기
	    return "/board/write";
	}
	
	@RequestMapping("detail")
	public String detail(Model model, int seq, HttpServletResponse response) {
		model.addAttribute("detail", service.detail(seq));
		service.viewCnt(seq);
		System.out.println(service.detail(seq));
		// 파일 목록 보여주기
		List<Map<String, Object>> fileList = service.selectFileList(seq);
		System.out.println(fileList);
		
		model.addAttribute("fileList", fileList);
		
		return "board/detail";
	}
	
	@RequestMapping("update")
	public String update(@RequestParam Map<String, Object> map, Model model, int seq) {
		model.addAttribute("detail", service.detail(seq));
		if (map != null && map.get("boardSubject") != null) {
			// POST 요청 처리: 글 저장
			int update = service.update(map);
			if(update == 0) {
				// 삽입 실패 시 처리 로직 (예: 에러 메시지 추가)
	            model.addAttribute("errorMessage", "글 저장에 실패했습니다.");
	            return "/board/write"; // 실패 시 글쓰기 폼으로 돌아감
			} else {	
				// 삽입 성공 시 리스트로 리다이렉트
	            return "redirect:/list";
			}
		}
		// GET 요청 처리: 글쓰기 폼 보여주기
		
		return "board/write";
	}
	
	@RequestMapping("delete")
	public String delete(int seq) {
		service.delete(seq);
		
		return "redirect:/list";
	}
	
	// 키에 대한 값을 가져오고, 값이 없을 경우 기본값을 반환합니다.
	private int getIntValue(Map<String, Object> map, String key, int defaultValue) {
	    return map.containsKey(key) ? Integer.parseInt(map.get(key).toString()) : defaultValue;
	}
	
	@RequestMapping("nexaList")
	public void nexaList(HttpServletRequest request, HttpServletResponse response) throws PlatformException {
		HttpPlatformRequest req = new HttpPlatformRequest(request);
		req.receiveData();
		
		PlatformData pdata = req.getData();
		
		VariableList varList = pdata .getVariableList();

		//VariableList로부터 값을 직접 참조
		String searchType = varList.getString("searchType"); 
		String keyword = varList.getString("keyword"); 
		System.out.println(searchType);
		System.out.println(keyword);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("searchType", searchType);
		paramMap.put("keyword", keyword);		
		System.out.println(paramMap);
		
		List<Map<String, Object>> list = sqlSession.selectList("mapper.nexaList", paramMap);
		
		DataSet ds = new DataSet("javaList");
		ds.addColumn("seq", DataTypes.INT, 100);
		ds.addColumn("memName", DataTypes.STRING, 100);
		ds.addColumn("memId", DataTypes.STRING, 100);
		ds.addColumn("boardSubject", DataTypes.STRING, 100);
		ds.addColumn("boardContent", DataTypes.STRING, 100);
		ds.addColumn("regDate", DataTypes.STRING, 100);
		ds.addColumn("uptDate", DataTypes.STRING, 100);
		ds.addColumn("viewCnt", DataTypes.STRING, 100);
		
		
		for(int i = 0; i < list.size(); i++) {
			
			int row = ds.newRow();
			ds.set(row, "seq", Integer.parseInt(list.get(i).get("SEQ").toString()) );
			ds.set(row, "memName", list.get(i).get("MEM_NAME").toString());
			ds.set(row, "memId", list.get(i).get("MEM_ID").toString());
			ds.set(row, "boardSubject", list.get(i).get("BOARD_SUBJECT").toString());
			if (list.get(i).get("BOARD_CONTENT") != null) {
				ds.set(row, "boardContent", list.get(i).get("BOARD_CONTENT").toString());
			}
			if (list.get(i).get("REG_DATE") != null) {
				ds.set(row, "regDate", list.get(i).get("REG_DATE").toString());
			}
			if (list.get(i).get("UPT_DATE") != null) {
				ds.set(row, "uptDate", list.get(i).get("UPT_DATE").toString());
			}
			if (list.get(i).get("VIEW_CNT") != null) {
				ds.set(row, "viewCnt", list.get(i).get("VIEW_CNT").toString());
			}
		}	

		PlatformData pData = new PlatformData();
		pData.addDataSet(ds);
		
		HttpPlatformResponse res = new HttpPlatformResponse(response, req);
		res.setData(pData);
		res.sendData();
	}
	
	@RequestMapping(value = "/ajax")
	public String ajax(@RequestParam Map<String, Object> map, Model model, HttpServletResponse response) {
		System.out.println(map);
		
		// 기본값 설정
	    int nowPage = getIntValue(map, "nowPage", 1);
	    int listSize = getIntValue(map, "listSize", 10);
		
		// 페이지네이션
		int total = service.totalCount(map);
	    
		System.out.println("map.keyword = " + map.get("keyword"));
		
		PageVO page = new PageVO(total, nowPage, listSize);
		model.addAttribute("pagination", page);		
		
		 // map에 페이지 정보 추가
		map.put("start", page.getStart());
		map.put("end", page.getEnd());
		
		model.addAttribute("list", service.list(map));
		
		return "board/ajaxList";
	}
	
	// 파일 다운로드 
	@RequestMapping("/fileDownload")
	public void fileDownload(int seq, HttpServletResponse response) throws IOException {
		Map<String, Object> fileInfo = service.selectFileInfo(seq);
		System.out.println("fileInfo = " + fileInfo);
		String saveFileName = (String) fileInfo.get("saveName");
		String originalFileName = (String) fileInfo.get("realName");
		
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:/Users/dev/Desktop/img/"+saveFileName));
		
		
		// file 다운로드 설정
		//파일유형설정
		response.setContentType("application/octet-stream");
		//파일길이설정
		response.setContentLength(fileByte.length); 
		//데이터형식/성향설정 (attachment: 첨부파일) 이걸 넣어야 확장자까지 들어간다.
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		
		// 다운로드 시 저장되는 이름은 Response Header의 "Content-Disposition"에 명시
		//버퍼의 출력스트림을 출력
		response.getOutputStream().write(fileByte); 
		//버퍼에 남아있는 출력스트림을 출력
		response.getOutputStream().flush();
		//출력스트림을 닫는다
		response.getOutputStream().close();
	}
	
	@RequestMapping("/exceldown")
	public String excelDownload(@RequestParam Map<String, Object> map, Model model) {
		System.out.println("성공");
		// 기본값 설정
	    int nowPage = getIntValue(map, "nowPage", 1);
	    int listSize = getIntValue(map, "listSize", 10);
		
		// 페이지네이션
		int total = service.totalCount(map);
		
		PageVO page = new PageVO(total, nowPage, listSize);
		model.addAttribute("pagination", page);		
		
		 // map에 페이지 정보 추가
		map.put("start", page.getStart());
		map.put("end", page.getEnd());
		
		model.addAttribute("list", service.list(map));
		
		return "board/excel";
	}
	
	@RequestMapping("img/{fileSeq}")
	public void image(int seq, HttpServletResponse response) throws IOException {
		Map<String, Object> fileInfo = service.selectFileInfo(seq);
		String saveFileName = (String) fileInfo.get("saveName");
		
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:/Users/dev/Desktop/img/"+saveFileName));
		
		//파일유형설정
		response.setContentType("image/jpeg"); 
		
		//버퍼의 출력스트림을 출력
		response.getOutputStream().write(fileByte); 
		//버퍼에 남아있는 출력스트림을 출력
		response.getOutputStream().flush();
		//출력스트림을 닫는다
		response.getOutputStream().close();
	}
}
