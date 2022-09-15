package doo.doo.web;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import doo.doo.recommend.*;
import doo.doo.dao.*;
import doo.doo.vo.*;

@RestController
public class RecommendRestController {
	@Autowired
	private NaverDataClass nds;
	@Autowired
	private RecommendManager mgr;
	@Autowired
	private RecommendDAO dao;
	
	@GetMapping(value="recommend/recommend_sub.do", produces="text/plain;charset=UTF-8")
	public String recommend_sub(int type) {
		String[] type1 = {"휴식","퇴근길","휴가","여행","운동","카페","해변"};//상황
		String[] type2 = {"슬픔","지침","아침","행복","추억","외로움","기분전환","축하"};//감성
		String[] type3 = {"따뜻한","편안한","로맨틱한","시원한","발렌타인데이","화이트데이","데이트","밝은"};//스타일
		String[] type4 = {"봄", "여름", "가을", "겨울", "추운날", "비오는날", "눈오는날"};//날씨/계절
		List<String> list = new ArrayList<String>();
		if(type==1) {
			list = Arrays.asList(type1);
		}else if(type==2) {
			list = Arrays.asList(type2);
		}else if(type==3) {
			list = Arrays.asList(type3);
		}else if(type==4){
			list = Arrays.asList(type4);
		}
		String result = "";
		try {
			JSONArray arr = new JSONArray();
			for(String s:list) {
				arr.add(s);
			}
			result = arr.toJSONString();
//			System.out.println(result);
		}catch(Exception ex) {}
		return result;
	}
	@PostMapping(value="recommend/recommend_data.do", produces="text/plain;charset=UTF-8")
	public String recommend_data(String fd) {
		String result="";
		//JSON 데이터를 우선 받아온다.
		String json = nds.recommendData(fd);
		//JSON 데이터 파싱
		List<String> list = mgr.jsonParser(json);
		//그 맛집 이름이 있는지 확인
		List<String> fList = dao.recommendNameData();
		
		try {
			Pattern[] p = new Pattern[fList.size()];
			for(int i=0;i<p.length;i++) {
				//내가 찾는 문자열을 집어넣어 주는 것 = 패턴
				p[i] = Pattern.compile(fList.get(i));
			}
			Matcher[] m = new Matcher[fList.size()];
			int[] count = new int[fList.size()];
			for(String s:list) {
				for(int i=0;i<m.length;i++) {
					m[i] = p[i].matcher(s);//p[i]가 m[i]에 포함되는지
					while(m[i].find()) {
						String ss = m[i].group();//실제 데이터를 가져옴
						count[i]++;
					}
				}
			}
			List<FoodVO> sList = new ArrayList<FoodVO>();
			//실제 추천할 데이터 출력
			for(int i=0;i<fList.size();i++) {
				String name = fList.get(i);
				if(count[i]>2) {
//					System.out.println(name+":"+count[i]);
					FoodVO vo = dao.recommendDetailData(name);
					sList.add(vo);
				}
			}
			JSONArray arr = new JSONArray();
			for(FoodVO vo:sList) {
				JSONObject obj = new JSONObject();
				obj.put("fno",vo.getFno());
				obj.put("name",vo.getName());
				obj.put("poster",vo.getPoster().substring(0,vo.getPoster().indexOf("^")));
				arr.add(obj); //이렇게 JSON으로 바꿔서 보내야 자바스크립트에서 취급할 수 있음
			}
			result = arr.toJSONString();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
