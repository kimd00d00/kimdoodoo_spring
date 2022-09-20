package doo.doo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import doo.doo.vo.*;

@Controller
public class SeoulController {
	@GetMapping("seoul/list.do")
	public String seoul_list() {
		return "seoul/list";
	}
	@GetMapping("seoul/detail_before.do")
	public String seoul_detail_before(int no, int type, RedirectAttributes ra, HttpServletResponse response) {
		String[] cmd = {"", "location", "nature", "shop"};
		Cookie cookie = new Cookie(cmd[type]+no, String.valueOf(no));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		
		ra.addAttribute("no",no);
		ra.addAttribute("type",type);
		return "redirect:../seoul/detail.do";
	}
	@GetMapping("seoul/detail.do")
	public String seoul_detail(int no, int type, Model model) {
		model.addAttribute("no", no);
		model.addAttribute("type", type);
		return "seoul/detail";
	}
	@GetMapping("seoul/login.do")
	public String seoul_login() {
		return "seoul/login";
	}
}
