package doo.doo.web;
import java.util.*;
import doo.doo.vo.*;
import doo.doo.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@Autowired
	private GoodsDAO dao;
	
	@GetMapping("/main/main")
	public String main_main(Model model) {
		Map map = new HashMap();
		//베스트
		map.put("table_name","goods_best");
		List<GoodsVO> bList = dao.goodsMainData(map);
		//신상품
		map.put("table_name","goods_new");
		List<GoodsVO> nList = dao.goodsMainData(map);
		//특가
		map.put("table_name","goods_special");
		List<GoodsVO> sList = dao.goodsMainData(map);
		
		model.addAttribute("bList",bList);
		model.addAttribute("nList",nList);
		model.addAttribute("sList",sList);
		model.addAttribute("main_jsp", "../goods/home.jsp");
		return "main/main";
	}
}
