package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import javax.servlet.http.HttpSession;

import doo.doo.vo.*;
import doo.doo.dao.*;

@Controller
public class MemberController {
	@Autowired
	private MemberDAO dao;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("member/join.do")
	public String member_join(Model model) {
		model.addAttribute("main_jsp","../member/join.jsp");
		return "main/main";
	}
	@PostMapping("member/join_ok.do")
	public String member_join_ok(MemberVO vo) {
		//전화번호 3칸에 나눠 받았으므로 합쳐서 vo에 삽입
		vo.setTel("010-"+vo.getTel1()+"-"+vo.getTel2());
		//비밀번호 암호화해서 vo에 삽입
		String en = encoder.encode(vo.getPwd());
		vo.setPwd(en);
		//INSERT
		dao.memberJoinInsert(vo);
		return "redirect:../main/main.do";
	}
	
	@PostMapping("member/idcheck.do")
	@ResponseBody
	public String member_idcheck(String id) {
		//@ResponseBody : HTML의 body태그 안에 값을 넣어주는것. 
		//JSP파일명이나 URI를 주는 게 아니라 일반 데이터나 JSON을 전송한다. 얘가 발전한 게 @RestController
		String result="";
		int count = dao.memberIdCheck(id);
		if(count==0) {
			result = "YES";
		}else {
			result = "NO";
		}
		return result;
	}
	
	@GetMapping("member/login.do")
	public String member_login(Model model) {
		model.addAttribute("main_jsp","../member/login.jsp");
		return "main/main";
	}
	
	@PostMapping("member/login_ok")
	@ResponseBody
	public String member_login_ok(String id, String pwd, HttpSession session) {
		String result = "";
		int count = dao.memberIdCheck(id);
		if(count==0) {
			result="NOID";
		}else {
			MemberVO vo = dao.memberJoinInfoData(id);
			if(encoder.matches(pwd, vo.getPwd())) {
				//암호화된 비밀번호 / 일반 비밀번호 비교
				session.setAttribute("id", id);
				session.setAttribute("name", vo.getName());
				session.setAttribute("role", vo.getRole());
				result = "OK";
			}else {
				result="NOPWD";
			}
		}
		return result;
	}
	
	@GetMapping("member/logout.do")
	public String member_logout(HttpSession session) {
		session.invalidate();
		return "redirect:../main/main.do";
	}
}
