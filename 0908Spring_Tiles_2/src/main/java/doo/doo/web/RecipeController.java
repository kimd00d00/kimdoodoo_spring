package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import doo.doo.vo.*;
import doo.doo.service.*;

@Controller
public class RecipeController {
	@Autowired
	private RecipeService service;
	
	@GetMapping("recipe/list.do")
	public String recipe_list(String page, Model model) {
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		int rowsize = 12;
		int start = rowsize*curpage-(rowsize-1);
		int end = rowsize*curpage;
		
		Map map = new HashMap();
		map.put("start",start);
		map.put("end",end);
		List<RecipeVO> list = service.recipeListData(map);
		for(RecipeVO vo:list) {
			String title = vo.getTitle();
			if(title.length()>20) {
				title = title.substring(0,20)+"..";
				vo.setTitle(title);
			}else {
				vo.setTitle(title);
			}
		}
		
		int totalpage=service.recipeTotalPage();
		final int BLOCK = 10;
		int startpage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endpage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("list",list);
		return "recipe/list";
	}
}
