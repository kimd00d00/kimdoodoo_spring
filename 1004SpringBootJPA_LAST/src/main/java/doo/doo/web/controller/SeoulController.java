package doo.doo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import doo.doo.web.dao.*;
import doo.doo.web.vo.*;

@Controller
public class SeoulController {
	@Autowired
	private SeoulLocationDAO dao;
	
	@GetMapping("/seoul/location")
	public String seoul_location(String page, Model model){
		if(page==null)
			page = "1";
		int curpage = Integer.parseInt(page);
		int rowsize = 12;
		int start = (rowsize*curpage)-rowsize; //0번부터 시작하기 때문에 -1 안해준다
		
		List<SeoulLocationEntity> list = dao.seoulListData(start);
		int totalpage = dao.seoulLocationTotalPage();
		
		final int BLOCK = 10;
		int startpage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endpage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endpage>totalpage)
			endpage = totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("list",list);
		
		model.addAttribute("main_content","seoul/location");
		return "main";
	}
	@GetMapping("/seoul/nature")
	public String seoul_nature(Model model){
		model.addAttribute("main_content","seoul/nature");
		return "main";
	}
	@GetMapping("/seoul/shop")
	public String seoul_shop(Model model){
		model.addAttribute("main_content","seoul/shop");
		return "main";
	}
}
