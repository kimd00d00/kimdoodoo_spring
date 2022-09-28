package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import doo.doo.vo.*;
import doo.doo.dao.*;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("board/list.do")
	public String board_list(String page, Model model) {
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		Map map = new HashMap();
		int rowsize = 10;
		int start = rowsize*curpage-(rowsize-1);
		int end = rowsize*curpage;
		
		map.put("start",start);
		map.put("end",end);
		List<BoardVO> list = dao.boardListData(map);
		int totalpage = (int)(Math.ceil(dao.boardRowCount()/10.0));
		
		model.addAttribute("curPage",curpage);
		model.addAttribute("totalPage",totalpage);
		model.addAttribute("list",list);
		model.addAttribute("main_jsp","../board/list.jsp");
		return "main/main";
	}
	
	@GetMapping("board/insert.do")
	public String board_insert(Model model) {
		model.addAttribute("main_jsp","../board/insert.jsp");
		return "main/main";
	}
	
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo);
		return "redirect:../board/list.do";
	}
	
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model) {
		BoardVO vo = dao.boardDetailData(no);
		int num = vo.getNum();
		BoardVO prevVO = new BoardVO();
		BoardVO nextVO = new BoardVO();
		
		int count = dao.boardRowCount();
		if(vo.getNum()==1) { //가장 최근 글
			prevVO.setSubject("");
			prevVO.setNo(0);
		}else {
			prevVO = dao.boardPNData(num-1);
		}
		if(vo.getNum()==count) { //가장 오래된 글
			nextVO.setSubject("");
			nextVO.setNo(0);
		}else {
			nextVO = dao.boardPNData(num+1);
		}
		
		model.addAttribute("prevVO",prevVO);
		model.addAttribute("nextVO",nextVO);
		
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../board/detail.jsp");
		return "main/main";
	}
}
