package doo.doo.jpa.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import doo.doo.jpa.dao.*;
import doo.doo.jpa.entity.*;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("/board/list")
	public String board_list(String page, Model model) {
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		int rowsize = 10;
		int start = (rowsize*curpage)-rowsize;
		
		List<BoardEntity> list = dao.boardListData(start);
		int totalpage = dao.boardTotalPage();
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list",list);
		return "board/list";
	}
	
	@GetMapping("/board/detail")
	public String board_detail(int no, Model model) {
		BoardEntity vo = dao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		dao.save(vo); //조회수 증가
		
		vo = dao.findByNo(no);
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	
	@GetMapping("/board/insert")
	public String board_insert() {
		return "board/insert";
	}
	
	@PostMapping("/board/insert_ok")
	public String board_insert_ok(BoardEntity en) {
		dao.save(en);
		return "redirect:/board/list";
	}
}
