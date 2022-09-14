package doo.doo.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import doo.doo.service.*;
import doo.doo.vo.*;
@RestController
public class FoodRestController {
	@Autowired
	private FoodService service;
	
	@GetMapping(value="food/food_find_vue.do", produces="text/plain;charset=UTF-8")
	public String food_find(String ss, String page) {
		String result="";
		try {
			if(page==null)
				page="1";
			if(ss==null)
				ss="강남";
			int curpage = Integer.parseInt(page);
			Map map = new HashMap();
			int rowsize = 12;
			int start = rowsize*curpage - (rowsize-1);
			int end = rowsize*curpage;
			map.put("start",start);
			map.put("end",end);
			map.put("address",ss);
			
			List<FoodVO> list = service.foodFindData(map);
			int totalpage = service.foodLocationTotalPage(ss);
			
			JSONArray arr = new JSONArray(); //List를 JSON으로 변경
			int k=0;
			for(FoodVO vo:list) {
				JSONObject obj = new JSONObject(); //{"no":1,"name":"kim"} 이렇게 됨
				obj.put("fno",vo.getFno());
				obj.put("name",vo.getName());
				obj.put("poster",vo.getPoster().substring(0,vo.getPoster().indexOf("^")));
				if(k==0) {
					obj.put("curpage",curpage);
					obj.put("totalpage",totalpage);
				}
				arr.add(obj);
				k++;
			}
			result = arr.toJSONString();
		}catch(Exception ex) {}
		return result;
	}
	
}
