package doo.doo.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import doo.doo.vo.*;
import doo.doo.dao.FoodDAO;

@RestController
//@CrossOrigin("http://localhost:3000")
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@PostMapping(value="food/recipe_list.do", produces="text/plain;charset=UTF-8")
	public String recipe_list(String type) {
		String result = "";
		String str = type.replace("/", "|").replace(" ", "").replace("요리","").replace("기타", "");
		List<String> list = dao.foodLikeRecipe(str);
		JSONArray arr = new JSONArray();
		for(String s:list) {
			arr.add(s);
		}
		result = arr.toJSONString();
		return result;
	}
	
	@GetMapping(value="food/detail_vue.do", produces="text/plain;charset=UTF-8")
	public String food_detail_vue(int fno) {
		String result = "";
		FoodVO vo = dao.foodDetailData(fno);
		String menu = vo.getMenu();
		menu = menu.replace("원", "^");
		menu = menu.substring(0,menu.lastIndexOf("^"));

		JSONObject obj = new JSONObject();
		obj.put("name",vo.getName());
		obj.put("poster",vo.getPoster());
		obj.put("fno",vo.getFno());
		obj.put("score",vo.getScore());
		obj.put("tel",vo.getTel());
		obj.put("type",vo.getType());
		obj.put("time",vo.getTime());
		obj.put("price",vo.getPrice());
		obj.put("parking",vo.getParking());
		obj.put("menu",menu);
		obj.put("address",vo.getAddress().substring(0,vo.getAddress().lastIndexOf("지")).trim());
		
		result = obj.toJSONString();
		return result;
	}
}
