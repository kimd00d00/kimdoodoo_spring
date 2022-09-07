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
	private GoodsDAO dao; //����� ���������Ƿ� GoodsController ������ ��� �� �� �ִ�.
	
	@GetMapping("goods/list.do")
	public String goods_list(String page, Model model, HttpServletRequest request) {
		/*null�� ���� �� �����Ƿ� �̷� ��쿡�� String���� �޴´�.
		�� request���� �޾ƿ���? ��Ű�� ���� ��ü�� �ƴϰ� request�� �����ؾ� �ϱ� ������. -> request.getCookie()
		�Ű������� ����� �� �ִ� ��
			1. ����ڰ� ���� �������� (int, String, double..)
			2. Servlet�� ������ �ִ� ���� ��ü(request, response, session, application, pageContext...)
			3. ��Ÿ : Error, RedirectAttributes
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
	
	//��Ű ���� (��Ű�� HTML �ѹ��� �ϳ����ۿ� �� ���� == detail_before �ʿ���)
	@GetMapping("goods/detail_before.do")
	public String goods_detail_before(int no, HttpServletResponse response) {
		//��Ű�� ���� : Ŭ���̾�Ʈ(=������)�� ������ �Ǵµ�, ���ڿ��� ������ �� �ִ�.
		Cookie cookie = new Cookie("goods"+no, String.valueOf(no));
		cookie.setPath("/"); //root�� ����
		cookie.setMaxAge(60*60*24);//�Ϸ絿�� ����
		response.addCookie(cookie);//����!
		return "redirect:detail.do?no="+no;
	}
	
	//�󼼺��� - <a> �±״� ������ GET���!!!
	@GetMapping("goods/detail.do")
	public String goods_detail(int no, Model model) {
		GoodsVO vo = dao.goodsDetailData(no);
		vo.setPrice(Integer.parseInt(vo.getGoods_price().replaceAll("[^0-9]", "").trim()));
		model.addAttribute("vo",vo);
		return "goods/detail";
	}
	
	//��Ű �Ѱ��� ����
	@GetMapping("goods/cookie_delete.do")
	public String goods_cookie_delete(int no, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		// ������ cookies�� null�� �ƴ�.
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
	
	//��Ű ��ü����
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
	
	//��ٱ���
	@GetMapping("goods/cart_list.do")
	public String goods_cart_list(int no, HttpSession session, Model model) {
		//���� �ӿ� ����� ��ٱ��� ����Ʈ�� �ҷ� �;� ��
		List<CartVO> list = (List<CartVO>)session.getAttribute("cart");
//		if(list==null) {
//			list = new ArrayList<CartVO>();
//		}
		//������ ����
		model.addAttribute("no",no);
		model.addAttribute("list",list);
		return "goods/cart_list";
	}
	
	//��ٱ��� �߰�
	@PostMapping("goods/session_insert.do")
	public String goods_session_insert(int no, int account, HttpSession session, Model model) {
		List<CartVO> list = (List<CartVO>)session.getAttribute("cart");
		//ó�� ���Ŀ��� ���ǿ� ����� �����͸� �ҷ��� -> �� ó������ �޸� �Ҵ��� �ϵ��� �Ѵ�.
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
			//�̹� ��ٱ��Ͽ� �� �ִ� ��ǰ�� �� ���� ��� ������ ������Ų��
			if(avo.getNo()==cvo.getNo()) {
				int acc = avo.getAccount()+cvo.getAccount();
				avo.setAccount(acc);
				bCheck = true;
				break;
			}
		}
		//���ο� ��ǰ�� ���� ���
		if(bCheck==false) {
			list.add(cvo);
			session.setAttribute("cart", list);
		}
		//������ ����
//		model.addAttribute("list",list);
//		model.addAttribute("no",no);
		//redirect�� ���� ���� Model�� ���� �ָ� �ȵȴ�. request�� �ʱ�ȭ�� �� ���۵Ǵϱ�...�Ұ�����.
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
		session.removeAttribute("cart"); //cart������ ����
		return "redirect:cart_list.do?no="+no;
	}
}
