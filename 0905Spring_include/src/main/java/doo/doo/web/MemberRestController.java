package doo.doo.web;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import doo.doo.dao.*;

@RestController
public class MemberRestController {
	@Autowired
	private GoodsDAO dao;
	
	@PostMapping(value="member/login_ok.do", produces="text/html;charset=UTF-8")
	public String member_login_ok(String id, String pwd, HttpSession session) {
		String result = "";
		MemberVO vo = dao.memberLogin(id, pwd);
		if(vo.getMsg().equals("NOID")) {
			result = "<script>"
					+ "alert(\"ID�� �������� �ʾƿ�!\");"
					+ "history.back();"
					+ "</script>";
		}else if(vo.getMsg().equals("NOPWD")) {
			result = "<script>"
					+ "alert(\"PWD�� Ʋ����!\");"
					+ "history.back();"
					+ "</script>";
		}else {
			session.setAttribute("id", id);
			session.setAttribute("name", vo.getName());
			//�̵�
			result = "<script>"
					+ "location.href=\"../main/main.do\";"
					+ "</script>";
		}
		return result;
	}
	
	@PostMapping(value="member/ajax_login_ok.do", produces="text/html;charset=UTF-8")
	public String member_ajax_login(String id, String pwd, HttpSession session) {
		String result="";
		MemberVO vo = dao.memberLogin(id, pwd);
		result = vo.getMsg();
		if(vo.getMsg().equals("OK")) {
			session.setAttribute("id", id);
			session.setAttribute("name", vo.getName());
			result = vo.getName();
		}
		return result;
	}
	
	//text/plain : JSON ������ ��, text/html : �Ϲ� ���ڿ� �Ǵ� script ������ ��
	@GetMapping(value="member/vue_login_ok.do", produces="text/plain;charset=UTF-8")
	public String member_vue_login(String id, String pwd, HttpSession session) {
		String result="";
		MemberVO vo = dao.memberLogin(id, pwd);
		result = vo.getMsg();
		if(vo.getMsg().equals("OK")) {
			session.setAttribute("id", id);
			session.setAttribute("name", vo.getName());
			result = vo.getName();
		}
		JSONObject obj = new JSONObject();
		obj.put("msg",vo.getMsg());
		obj.put("name",vo.getName());
		result = obj.toJSONString();
		
		return result;
	}
}