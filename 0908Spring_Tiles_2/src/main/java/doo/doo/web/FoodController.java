package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import doo.doo.service.*;
import doo.doo.vo.*;

@Controller
public class FoodController {
	//사용자의 요청(요구사항) = 클라이언트로부터 서버가 요청을 받는 곳은 주소창이다! 그래서 주소로 다 찾는것임.
	@Autowired
	private FoodService service;
	@Autowired
	private RecipeService rService;
	
	@GetMapping("food/food_list.do")
	public String food_food_list(int cno, Model model) {
		List<FoodVO> list = service.foodListData(cno);
		CategoryVO cvo = service.categoryInfoData(cno);
		model.addAttribute("list",list);
		model.addAttribute("cvo", cvo);
		return "food/food_list";
	}
	
	@GetMapping("food/food_detail.do")
	public String food_detail(int fno, Model model) {
		FoodVO vo = service.foodDetailData(fno);
		model.addAttribute("vo",vo);
		
		String ss = vo.getType();
		ss = ss.replace("/", "|");
		List<RecipeVO> rList = rService.recipeFindData(ss);
		model.addAttribute("rList",rList);
		
		return "food/food_detail";
	}
	
	@RequestMapping("food/food_find.do")
	public String food_find(String page, String ss, Model model) {
		if(ss==null)
			ss = "강남";
		if(page==null)
			page = "1";
		
		int curpage = Integer.parseInt(page); 
		Map map = new HashMap();
		int rowsize = 12;
		int start = curpage*rowsize - (rowsize-1);
		int end = curpage*rowsize;
		map.put("start",start);
		map.put("end",end);
		map.put("address",ss);
		
		List<FoodVO> list = service.foodFindData(map);
		int totalpage = service.foodLocationTotalPage(ss);
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("ss",ss);
		return "food/food_find";
	}
	
	@GetMapping("food/food_find_vue.do")
	public String food_find_vue() {
		return "food/food_find_vue";
	}
	
	@GetMapping("food/food_detail_vue.do")
	public String food_detail_vue(int fno, Model model) {
		model.addAttribute("fno",fno);
		return "food/food_detail_vue";
	}
}
