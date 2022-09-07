package doo.doo.web;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import doo.doo.dao.*;
@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	//스프링에서 생성된 객체를 받아서 사용한다. => 자동으로 주소값을 얻어 온다
	@GetMapping("food/list.do")
	public String food_list(String page, Model model, HttpServletRequest request) {
		//전송할 게 있으면 model을 갖다놓고 시작해야함
		if(page==null)
			page="1";
		int curPage = Integer.parseInt(page);

		Map map = new HashMap();
		int rowSize = 12;
		int start= (rowSize*curPage)-(rowSize-1);
		int end = rowSize*curPage;
		map.put("start", start);
		map.put("end",end);
		int totalPage = dao.foodTotalPage();
		List<FoodVO> list = dao.foodListData(map);
		
		//Cookie
		Cookie[] cookies = request.getCookies();
		List<FoodVO> cList = new ArrayList<FoodVO>();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("food")) {
					String fno = cookies[i].getValue();
					FoodVO vo = dao.foodDetail(Integer.parseInt(fno));
					cList.add(vo);
				}
			}
		}
		
		//////////////////////////////////////////////
		model.addAttribute("curPage",curPage);
		model.addAttribute("list",list);
		model.addAttribute("cList",cList);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("size",cList.size());
		//////////////////////////////////////////////
		return "list";
	}
	
	@GetMapping("food/find.do")
	public String food_find() {
		return "find";
	}
	
	//쿠키 저장 (쿠키랑 HTML 한번에 하나씩밖에 못 보냄 == detail_before 필요함)
	@GetMapping("food/detail_before.do")
	public String food_detail_before(int fno, HttpServletResponse response) {
		//쿠키의 단점 : 클라이언트(=브라우저)에 저장이 되는데, 문자열만 저장할 수 있다.
		Cookie cookie = new Cookie("food"+fno, String.valueOf(fno));
		cookie.setPath("/"); //root에 저장
		cookie.setMaxAge(60*60*24);//하루동안 저장
		response.addCookie(cookie);//전송!
		return "redirect:detail.do?fno="+fno;
	}
	
	@GetMapping("food/detail.do")
	public String food_detail(int fno, Model model) {
		//parseInt, getParameter 다 알아서 해 줌.
		FoodVO vo = dao.foodDetail(fno);
		
		model.addAttribute("vo",vo);
		return "detail";
	}
	
	//쿠키 전체삭제
	@GetMapping("food/cookie_all_delete.do")
	public String food_cookie_all_delete(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for(int i=cookies.length-1; i>=0; i--) {
			if(cookies[i].getName().startsWith("food")) {
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
		return "redirect:list.do";
	}
	//쿠키 한개씩 삭제
	@GetMapping("food/cookie_delete.do")
	public String food_cookie_delete(int fno, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		// 지금은 cookies가 null이 아님.
		for(int i=cookies.length-1; i>=0; i--) {
			if(cookies[i].getName().equals("food"+fno)) {
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
				break;
			}
		}
		return "redirect:list.do";
	}
}
