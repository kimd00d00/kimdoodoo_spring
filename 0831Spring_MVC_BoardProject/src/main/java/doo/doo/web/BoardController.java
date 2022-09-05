package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
//request에 값을 담아 JSP로 전송할것임
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import doo.doo.dao.*;
@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	//목록
	@GetMapping("board/list.do")
	public String board_list(String page, Model model) {
		//null값으로 들어올 수 있는 부분은 String으로 받아야 한다.
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);
		Map map = new HashMap();
		map.put("start", ((curPage*10)-9));
		map.put("end",curPage*10);
		List<BoardVO> list = dao.boardListData(map);
		int totalPage = dao.boardTotalPage();
		
		model.addAttribute("curPage",curPage);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("list",list);
		
		return "board/list";
	}
	
	//게시글 추가 화면(입력 폼 출력)
	@GetMapping("board/insert.do")
	public String board_insert() {
		return "board/insert";
	}
	//게시글 추가 처리
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo); //vo == 커맨드 객체
		return "redirect:list.do";
	}
	
	//게시글 상세보기
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model) {
		BoardVO vo = dao.boardDetailData(no);
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	
	//게시글 수정 화면
	@GetMapping("board/update.do")
	public String board_update(int no, Model model) {
		BoardVO vo = dao.boardUpdateData(no);
		model.addAttribute("vo",vo);
		return "board/update";
	}
	
	//게시글 검색
	@PostMapping("board/find.do")
	public String board_find(String[] fd, String ss, Model model) {
		//체크박스는 배열로 잡아 줘야 한다
		Map map = new HashMap();
		map.put("fsArr",fd);
		map.put("ss",ss);
		List<BoardVO> list = dao.boardFindData(map);
		model.addAttribute("list",list);
		return "board/find";
	}
	
	//게시글 삭제화면으로 이동
	@GetMapping("board/delete.do")
	public String board_delete(int no, Model model) {
		BoardVO vo = dao.boardDeleteData(no);
		model.addAttribute("vo",vo);
		return "board/delete";
	}
}