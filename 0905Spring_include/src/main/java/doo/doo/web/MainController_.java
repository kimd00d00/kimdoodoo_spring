package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import doo.doo.dao.*;
import java.util.*;

public class MainController_ {
	@Autowired
	private SeoulDAO dao;
	
	@GetMapping("main/main.do")
	public String main(Model model) {
		List<SeoulVO> list = dao.locationData();
		model.addAttribute("main_jsp","../main/home.jsp");
		model.addAttribute("list",list);
		return "main/main";
	}
}
