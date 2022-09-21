package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import doo.doo.vo.*;
import doo.doo.dao.*;
@Controller
public class MainController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping("main/main.do")
	public String main_main(Model model) {
		List<CategoryVO> list = dao.categoryAllData();
		model.addAttribute("list",list);
		model.addAttribute("main_jsp","../main/home.jsp");
		return "main/main";
	}
}
