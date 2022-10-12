package doo.doo.app2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import doo.doo.dao.*;

@Controller
public class MainController {
	@Autowired
	private MemberDAO dao;
	
	@GetMapping("member/list.do")
	public String member_list(Model model) {
		List<MemberVO> list = dao.memberListData();
		model.addAttribute("list",list);
		return "member/list";
	}
}
