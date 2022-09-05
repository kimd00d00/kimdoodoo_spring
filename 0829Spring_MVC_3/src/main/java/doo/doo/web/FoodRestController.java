package doo.doo.web;

import org.json.simple.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import doo.doo.dao.FoodDAO;
import doo.doo.dao.FoodVO;

import java.util.*;
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	@GetMapping(value="food/food_find_vue.do",produces="text/plain;charset=UTF-8")
	public String food_find(int page, String loc) {
		String result="";
		try {
			int rowSize = 9;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			Map map = new HashMap();
			map.put("start",start);
			map.put("end",end);
			map.put("address",loc);
			List<FoodVO> list = dao.foodFindData(map);
			//List -> 배열로 보내진다
			//VO -> {} 객체로 보내진다
			JSONArray arr = new JSONArray();
			for(FoodVO vo:list) {
				JSONObject obj = new JSONObject();
				obj.put("fno",vo.getFno());
				obj.put("poster",vo.getPoster());
				obj.put("name",vo.getName());
				arr.add(obj); //[{}, {}, {},...] 이렇게 넘어가는거임
			}
			result = arr.toJSONString();
		}catch(Exception ex) {}
		return result;
	}
}
