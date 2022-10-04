package doo.doo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import doo.doo.web.dao.*;
import doo.doo.web.vo.*;
import java.util.*;

@Controller
public class MainController {
	@Autowired
	private FoodCategoryDAO dao;
	
	@GetMapping("/main")
	public String main_page(String cate, Model model) {
		if(cate==null)
			cate="1";
		int cno = Integer.parseInt(cate);
		
		int start = 0;
		int end = 0;
		
		if(cno==1) {
			start = 0;
			end = 12;
		}else if(cno==2) {
			start = 12;
			end = 6;
		}else if(cno==3) {
			start = 18;
			end = 12;
		}
		List<CategoryEntity> list = dao.categoryListData(start, end);
		model.addAttribute("list",list);
		model.addAttribute("main_content","main/home");
		return "main";
	}
}
