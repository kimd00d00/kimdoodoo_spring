package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import doo.doo.dao.*;

@Controller
public class GoodsController {
	@Autowired
	private GoodsDAO dao;
	
	@GetMapping("goods/best.do")
	public String goods_best(String page, Model model) {
		CommonsController.commonsData("goods_best", page, dao, model);
		model.addAttribute("main_jsp","../goods/best.jsp");
		return "main/main";
	}
	@GetMapping("goods/special.do")
	public String goods_special(String page, Model model) {
		CommonsController.commonsData("goods_special", page, dao, model);
		model.addAttribute("main_jsp","../goods/special.jsp");
		return "main/main";
	}
	@GetMapping("goods/new.do")
	public String goods_new(String page, Model model) {
		CommonsController.commonsData("goods_new", page, dao, model);
		model.addAttribute("main_jsp","../goods/new.jsp");
		return "main/main";
	}
	@GetMapping("goods/best_detail.do")
	public String goods_best_detail(int no, Model model) {
		CommonsController.commonsDetailData("goods_best", no, dao, model);
		model.addAttribute("main_jsp","../goods/best_detail.jsp");
		return "main/main";
	}
	@GetMapping("goods/special_detail.do")
	public String goods_special_detail(int no, Model model) {
		CommonsController.commonsDetailData("goods_special", no, dao, model);
		model.addAttribute("main_jsp","../goods/special_detail.jsp");
		return "main/main";
	}
	@GetMapping("goods/new_detail.do")
	public String goods_new_detail(int no, Model model) {
		CommonsController.commonsDetailData("goods_new", no, dao, model);
		model.addAttribute("main_jsp","../goods/new_detail.jsp");
		return "main/main";
	}
	@RequestMapping("goods/find.do")
	public String goods_find(String fs, String ss, Model model) {
		if(fs!=null && ss!=null) {
			Map map = new HashMap();
			map.put("table_name",fs);
			map.put("ss",ss);
			List<GoodsVO> list = dao.goodsFindData(map);
			model.addAttribute("list",list);
		}
		model.addAttribute("main_jsp","../goods/find.jsp");
		return "main/main";
	}
}
