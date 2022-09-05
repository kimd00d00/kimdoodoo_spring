package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("board/list.do")
	public String board_list(String page, Model model) {
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page); //int page로 받으면 400 Bad Request 발생할 수 있음
		List<BoardVO> list = dao.boardListData(curPage);
		int totalPage = dao.boardTotalPage();
		
		int totalCount = dao.boardCount();
		totalCount = totalCount-((10*curPage)-10);
		
		model.addAttribute("totalCount",totalCount);
		model.addAttribute("list",list);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("curPage",curPage);
		return "board/list";
	}
	
	//입력폼만 띄워줄 것
	@GetMapping("board/insert.do")
	public String board_insert() {
		return "board/insert";
	}
	
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo);
		return "redirect:list.do"; //redirect == request 를 전송하지 않음!
	}
	
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model) {
		BoardVO vo = dao.boardDetailData(no);
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	
	@GetMapping("board/reply.do")
	public String board_reply(int no, Model model) {
		model.addAttribute("no",no);
		return "board/reply";
	}
	
	@PostMapping("board/reply_ok.do")
	public String board_reply_ok(int pno, BoardVO vo) {
		try {
			dao.boardReplyInsert(pno, vo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:list.do";
	}
	
	@GetMapping("board/update.do")
	public String board_update(int no, Model model) {
		BoardVO vo = dao.boardUpdateData(no);
		model.addAttribute("vo",vo);
		return "board/update";
	}
	
	@PostMapping("board/update_ok.do")
	public String board_update_ok(BoardVO vo, Model model) {
		boolean bCheck = dao.boardUpdate(vo);
		model.addAttribute("bCheck",bCheck);
		model.addAttribute("no",vo.getNo());
		return "board/update_ok";
	}
	
	@GetMapping("board/delete.do")
	public String board_delete(int no, Model model) {
		model.addAttribute("no",no);
		return "board/delete";
	}
	
	@PostMapping("board/delete_ok.do")
	public String board_delete_ok(int no, String pwd, Model model) {
		boolean bCheck = dao.boardDelete(no, pwd);
		model.addAttribute("bCheck",bCheck);
		return "board/delete_ok";
	}
}
