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
	//���������� ������ ��ü�� �޾Ƽ� ����Ѵ�. => �ڵ����� �ּҰ��� ��� �´�
	@GetMapping("food/list.do")
	public String food_list(String page, Model model, HttpServletRequest request) {
		//������ �� ������ model�� ���ٳ��� �����ؾ���
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
	
	//��Ű ���� (��Ű�� HTML �ѹ��� �ϳ����ۿ� �� ���� == detail_before �ʿ���)
	@GetMapping("food/detail_before.do")
	public String food_detail_before(int fno, HttpServletResponse response) {
		//��Ű�� ���� : Ŭ���̾�Ʈ(=������)�� ������ �Ǵµ�, ���ڿ��� ������ �� �ִ�.
		Cookie cookie = new Cookie("food"+fno, String.valueOf(fno));
		cookie.setPath("/"); //root�� ����
		cookie.setMaxAge(60*60*24);//�Ϸ絿�� ����
		response.addCookie(cookie);//����!
		return "redirect:detail.do?fno="+fno;
	}
	
	@GetMapping("food/detail.do")
	public String food_detail(int fno, Model model) {
		//parseInt, getParameter �� �˾Ƽ� �� ��.
		FoodVO vo = dao.foodDetail(fno);
		
		model.addAttribute("vo",vo);
		return "detail";
	}
	
	//��Ű ��ü����
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
	//��Ű �Ѱ��� ����
	@GetMapping("food/cookie_delete.do")
	public String food_cookie_delete(int fno, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		// ������ cookies�� null�� �ƴ�.
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
