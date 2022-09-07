package doo.doo.web;

import java.util.*;

import javax.servlet.http.HttpSession;

import doo.doo.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReplyController {
	@Autowired
	private ReplyDAO dao;//다른 Controller에서 주소값을 부여해도 다 같은 주소를 가진다! 굿~
	
	//댓글 달기
	@PostMapping("reply/reply_insert.do")
	public String reply_insert(ReplyVO vo, HttpSession session) {
		//ID와 name을 갖고 와야 하므로 session 갖고옴
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		vo.setId(id);
		vo.setName(name);
		dao.replyInsert(vo);
		String uri="";
		if(vo.getType()==1) { //all
			uri="../goods/all_detail.do?no="+vo.getBno();
		}else if(vo.getType()==2) { //new
			uri="../goods/new_detail.do?no="+vo.getBno();
		}else if(vo.getType()==3) { //special
			uri="../goods/special_detail.do?no="+vo.getBno();
		}else if(vo.getType()==4) { //best
			uri="../goods/best_detail.do?no="+vo.getBno();
		}
		
		return "redirect:"+uri;
	}
	
	//댓글 수정
	@PostMapping("reply/reply_update.do")
	public String reply_update(ReplyVO vo) {
		dao.replyUpdate(vo);
		String uri="";
		if(vo.getType()==1) { //all
			uri="../goods/all_detail.do?no="+vo.getBno();
		}else if(vo.getType()==2) { //new
			uri="../goods/new_detail.do?no="+vo.getBno();
		}else if(vo.getType()==3) { //special
			uri="../goods/special_detail.do?no="+vo.getBno();
		}else if(vo.getType()==4) { //best
			uri="../goods/best_detail.do?no="+vo.getBno();
		}
		return "redirect:"+uri;
	}

	//대댓글 기능
	@PostMapping("reply/reply_reply_insert.do")
	public String reply_reply_insert(int pno, ReplyVO vo, HttpSession session) {
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		vo.setId(id);
		vo.setName(name);
		dao.replyreplyInsert(pno, vo);
		String uri="";
		if(vo.getType()==1) { //all
			uri="../goods/all_detail.do?no="+vo.getBno();
		}else if(vo.getType()==2) { //new
			uri="../goods/new_detail.do?no="+vo.getBno();
		}else if(vo.getType()==3) { //special
			uri="../goods/special_detail.do?no="+vo.getBno();
		}else if(vo.getType()==4) { //best
			uri="../goods/best_detail.do?no="+vo.getBno();
		}
		return "redirect:"+uri;
	}
	
	//삭제 기능
	@GetMapping("reply/reply_delete.do")
	public String reply_delete(ReplyVO vo) {
		int no = vo.getNo();
		dao.replyDelete(no);
		String uri="";
		if(vo.getType()==1) { //all
			uri="../goods/all_detail.do?no="+vo.getBno();
		}else if(vo.getType()==2) { //new
			uri="../goods/new_detail.do?no="+vo.getBno();
		}else if(vo.getType()==3) { //special
			uri="../goods/special_detail.do?no="+vo.getBno();
		}else if(vo.getType()==4) { //best
			uri="../goods/best_detail.do?no="+vo.getBno();
		}
		return "redirect:"+uri;
	}
}
