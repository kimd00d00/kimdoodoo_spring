package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import doo.doo.dao.ReplyDAO;
import doo.doo.vo.ReplyVO;
import java.util.*;

import javax.servlet.http.HttpSession;

@Controller
public class ReplyController {
	@Autowired
	private ReplyDAO dao;
	
	@PostMapping("reply/insert.do")
	public String reply_insert(ReplyVO vo, HttpSession session, RedirectAttributes ra) {
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		vo.setId(id);
		vo.setName(name);
		dao.replyInsert(vo);
		//RedirectAttributes : redirect하면서 데이터를 넘길 때 사용함
		ra.addAttribute("tab", vo.getType());
		ra.addAttribute("no",vo.getCno());
		return "redirect:../seoul/detail.do";
//		return "redirect:../seoul/detail.do?tab="+vo.getType()+"&no="+vo.getCno();
	}
	
	@GetMapping("reply/delete.do")
	public String reply_delete(ReplyVO vo, RedirectAttributes ra) {
		dao.replyDelete(vo.getNo());
		ra.addAttribute("tab",vo.getType());
		ra.addAttribute("no",vo.getCno());
		return "redirect:../seoul/detail.do";
	}
	
	@PostMapping("reply/update.do")
	public String reply_update(ReplyVO vo, RedirectAttributes ra) {
		dao.replyUpdate(vo);
		ra.addAttribute("tab",vo.getType());
		ra.addAttribute("no",vo.getCno());
		return "redirect:../seoul/detail.do";
	}
	
}
