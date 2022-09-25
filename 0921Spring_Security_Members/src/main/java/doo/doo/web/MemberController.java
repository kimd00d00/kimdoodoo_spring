package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
	public String member_login_ok(String id, String pwd, Boolean ck, HttpSession session, HttpServletResponse response) {
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
				if(ck==true) {
					//쿠키 저장
					Cookie cookie = new Cookie("id",id);
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
					///////////////////////////////
					cookie = new Cookie("name",vo.getName());
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
					//////////////////////////////
					cookie = new Cookie("role",vo.getRole());
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
				}
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
	
	@GetMapping("member/join_before.do")
	public String member_join_before(Model model) {
		model.addAttribute("main_jsp","../member/join_before.jsp");
		return "main/main";
	}
	
	@GetMapping("member/join_before_ok.do")
	@ResponseBody
	public String member_join_before_ok(String pwd, HttpSession session) {
		String id = (String)session.getAttribute("id");
		String result = "";
		String db_pwd = dao.memberGetPassword(id);
		if(encoder.matches(pwd, db_pwd)) {
			result = "yes";
		}else {
			result = "no";
		}
		return result;
	}
	
	@GetMapping("member/join_update.do")
	public String member_join_update(Model model, HttpSession session) {
		String id = (String)session.getAttribute("id");
		MemberVO vo = dao.memberUpdateData(id);
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../member/join_update.jsp");
		return "main/main";
	}
	
	@PostMapping("member/join_update_ok.do")
	public String member_update(MemberVO vo, HttpSession session) {
		String tel = "010-"+vo.getTel1()+"-"+vo.getTel2();
		vo.setTel(tel);
		dao.memberUpdate(vo);
		session.setAttribute("name", vo.getName());
		return "redirect:../main/main.do";
	}
}
