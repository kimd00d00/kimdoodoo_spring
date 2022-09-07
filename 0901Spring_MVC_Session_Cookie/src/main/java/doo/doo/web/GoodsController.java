package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import doo.doo.dao.*;

@Controller
public class GoodsController {
	@Autowired
	private GoodsDAO dao; //멤버로 선언했으므로 GoodsController 내에서 계속 쓸 수 있다.
	
	@GetMapping("goods/list.do")
	public String goods_list(String page, Model model, HttpServletRequest request) {
		/*null로 들어올 수 있으므로 이런 경우에는 String으로 받는다.
		왜 request까지 받아오나? 쿠키는 내장 객체가 아니고 request로 생성해야 하기 때문임. -> request.getCookie()
		매개변수로 사용할 수 있는 것
			1. 사용자가 보낸 데이터형 (int, String, double..)
			2. Servlet이 가지고 있는 내장 객체(request, response, session, application, pageContext...)
			3. 기타 : Error, RedirectAttributes
		*/
		if(page==null)
			page = "1";
		int curPage = Integer.parseInt(page);
		int totalPage = dao.goodsTotalPage();
		
		Map map = new HashMap();
		int rowSize = 12;
		int start = (rowSize*curPage) - (rowSize-1);
		int end = rowSize*curPage;
		map.put("start",start);
		map.put("end",end);
		List<GoodsVO> list = dao.goodsListData(map);
		
		for(GoodsVO vo:list) {
			String name = vo.getGoods_name();
			if(name.length()>20) {
				name = name.substring(0,20)+"..";
			}
			vo.setGoods_name(name);
		}
		//Cookie
		Cookie[] cookies = request.getCookies();
		List<GoodsVO> cList = new ArrayList<GoodsVO>();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("goods")) {
					String no = cookies[i].getValue();
					GoodsVO vo = dao.goodsDetailData(Integer.parseInt(no));
					cList.add(vo);
				}
			}
		}
		
		model.addAttribute("curPage", curPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list",list);
		model.addAttribute("cList",cList);
		model.addAttribute("size",cList.size());
		
		return "goods/list";
	}
	
	//쿠키 저장 (쿠키랑 HTML 한번에 하나씩밖에 못 보냄 == detail_before 필요함)
	@GetMapping("goods/detail_before.do")
	public String goods_detail_before(int no, HttpServletResponse response) {
		//쿠키의 단점 : 클라이언트(=브라우저)에 저장이 되는데, 문자열만 저장할 수 있다.
		Cookie cookie = new Cookie("goods"+no, String.valueOf(no));
		cookie.setPath("/"); //root에 저장
		cookie.setMaxAge(60*60*24);//하루동안 저장
		response.addCookie(cookie);//전송!
		return "redirect:detail.do?no="+no;
	}
	
	//상세보기 - <a> 태그는 무조건 GET방식!!!
	@GetMapping("goods/detail.do")
	public String goods_detail(int no, Model model) {
		GoodsVO vo = dao.goodsDetailData(no);
		vo.setPrice(Integer.parseInt(vo.getGoods_price().replaceAll("[^0-9]", "").trim()));
		model.addAttribute("vo",vo);
		return "goods/detail";
	}
	
	//쿠키 한개씩 삭제
	@GetMapping("goods/cookie_delete.do")
	public String goods_cookie_delete(int no, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		// 지금은 cookies가 null이 아님.
		for(int i=cookies.length-1; i>=0; i--) {
			if(cookies[i].getName().equals("goods"+no)) {
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
				break;
			}
		}
		return "redirect:list.do";
	}
	
	//쿠키 전체삭제
	@GetMapping("goods/cookie_all_delete.do")
	public String goods_cookie_all_delete(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for(int i=cookies.length-1; i>=0; i--) {
			if(cookies[i].getName().startsWith("goods")) {
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
		return "redirect:list.do";
	}
	
	//장바구니
	@GetMapping("goods/cart_list.do")
	public String goods_cart_list(int no, HttpSession session, Model model) {
		//세션 속에 저장된 장바구니 리스트를 불러 와야 함
		List<CartVO> list = (List<CartVO>)session.getAttribute("cart");
//		if(list==null) {
//			list = new ArrayList<CartVO>();
//		}
		//데이터 전송
		model.addAttribute("no",no);
		model.addAttribute("list",list);
		return "goods/cart_list";
	}
	
	//장바구니 추가
	@PostMapping("goods/session_insert.do")
	public String goods_session_insert(int no, int account, HttpSession session, Model model) {
		List<CartVO> list = (List<CartVO>)session.getAttribute("cart");
		//처음 이후에는 세션에 저장된 데이터를 불러옴 -> 맨 처음에만 메모리 할당을 하도록 한다.
		if(list==null) {
			list = new ArrayList<CartVO>();
		}
		//name, poster, price
		GoodsVO vo = dao.goodsDetailData(no);
		CartVO cvo = new CartVO();
		cvo.setNo(no);
		cvo.setName(vo.getGoods_name());
		cvo.setPoster(vo.getGoods_poster());
		cvo.setPrice(vo.getGoods_price());
		cvo.setAccount(account);
		
		boolean bCheck = false;
		for(CartVO avo:list) {
			//이미 장바구니에 들어가 있는 상품을 또 담은 경우 수량만 증가시킨다
			if(avo.getNo()==cvo.getNo()) {
				int acc = avo.getAccount()+cvo.getAccount();
				avo.setAccount(acc);
				bCheck = true;
				break;
			}
		}
		//새로운 상품을 담은 경우
		if(bCheck==false) {
			list.add(cvo);
			session.setAttribute("cart", list);
		}
		//데이터 전송
//		model.addAttribute("list",list);
//		model.addAttribute("no",no);
		//redirect가 나올 때는 Model로 보내 주면 안된다. request가 초기화된 후 전송되니까...불가능함.
		return "redirect:cart_list.do?no="+no;
	}
	
	
	@GetMapping("goods/cart_cancel.do")
	public String cart_cancel(int no, HttpSession session) {
		List<CartVO> list = (List<CartVO>)session.getAttribute("cart");
		for(int i=0;i<list.size();i++) {
			CartVO vo = list.get(i);
			if(vo.getNo()==no) {
				list.remove(i);
			}
		}
		return "redirect:cart_list.do?no="+no;
	}
	
	@GetMapping("goods/cart_total_delete.do")
	public String cart_total_delete(int no, HttpSession session) {
		session.removeAttribute("cart"); //cart정보만 삭제
		return "redirect:cart_list.do?no="+no;
	}
}
