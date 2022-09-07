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
	private ReplyDAO dao;//�ٸ� Controller���� �ּҰ��� �ο��ص� �� ���� �ּҸ� ������! ��~
	
	//��� �ޱ�
	@PostMapping("reply/reply_insert.do")
	public String reply_insert(ReplyVO vo, HttpSession session) {
		//ID�� name�� ���� �;� �ϹǷ� session �����
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
	
	//��� ����
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

	//���� ���
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
	
	//���� ���
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
