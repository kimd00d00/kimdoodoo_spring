package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import doo.doo.dao.*;
import doo.doo.vo.*;

@Controller //화면 이동!
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("board/list.do")
	public String board_list(String page, Model model) {
		return "board/list";
	}
	@GetMapping("board/insert.do")
	public String board_insert() {
		return "board/insert";
	}
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model) {
		model.addAttribute("no",no);
		return "board/detail";
	}
	@GetMapping("board/update.do")
	public String board_update(int no, Model model) {
		model.addAttribute("no",no);
		return "board/update";
	}
	@GetMapping("board/delete.do")
	public String board_delete(int no, Model model) {
		model.addAttribute("no",no);
		return "board/delete";
	}
}
