package doo.doo.web;

import java.util.*;
import doo.doo.vo.*;
import doo.doo.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("recipe/")
public class RecipeController {
	@Autowired
	private RecipeDAO dao;
	
	@RequestMapping("list.do")
	public String recipe_list(String page, String type, Model model) {
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		
		Map map = new HashMap();
		int rowsize = 12;
		int start = rowsize*curpage - (rowsize-1);
		int end = rowsize*curpage;
		map.put("start",start);
		map.put("end",end);
		int totalpage = 0;
		if(type==null||type.equals("")) {
			list = dao.recipeListData(map);
			totalpage = dao.recipeTotalPage();
		}else {
			map.put("ss",type);
			list = dao.recipeFindData(map);
			totalpage = dao.recipeFindTotalPage(map);
		}
		
		for(RecipeVO vo:list) {
			String title = vo.getTitle();
			if(title.length()>15) {
				title = title.substring(0,15)+"..";
				vo.setTitle(title);
			}
			vo.setTitle(title);
		}
		
		final int BLOCK = 10;
		int startpage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endpage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage = totalpage;
		
		model.addAttribute("main_jsp","../recipe/list.jsp");
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("type",type);
		return "main/main";
	}
	
	@GetMapping("chef_list.do")
	public String chef_list(String page, Model model) {
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		
		Map map = new HashMap();
		int rowsize = 12;
		int start = rowsize*curpage - (rowsize-1);
		int end = rowsize*curpage;
		map.put("start",start);
		map.put("end",end);
		
		List<ChefVO> list = dao.chefListData(map);
		int totalpage = dao.chefTotalPage();
		
		model.addAttribute("main_jsp","../recipe/chef_list.jsp");
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		return "main/main";
	}
	
	@RequestMapping("chef_detail.do")
	public String chef_detail(int no, String page, String ss, Model model) {
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		
		Map map = new HashMap();
		int rowsize = 12;
		int start = rowsize*curpage - (rowsize-1);
		int end = rowsize*curpage;
		map.put("start",start);
		map.put("end",end);
		map.put("no",no);
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		int totalpage = 0;
		
		if(ss==null||ss.equals("")) {
			list = dao.chefMakeRecipeData(map);
			totalpage = dao.chefMakeRecipeTotalPage(map);
		}else {
			map.put("ss",ss);
			list = dao.chefMakeRecipeFindData(map);
			totalpage = dao.chefMakeRecipeFindTotalPage(map);
		}
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("no",no); //chef_no
		model.addAttribute("ss",ss);
		model.addAttribute("main_jsp","../recipe/chef_detail.jsp");
		return "main/main";
	}
}
