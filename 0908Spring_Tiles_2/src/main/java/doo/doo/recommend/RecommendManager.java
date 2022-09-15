package doo.doo.recommend;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RecommendManager {
	public List<String> jsonParser(String json){
		List<String> list = new ArrayList<String>();
		try {
			//{}로 시작하면 Object, []로 시작하면 Array
			JSONParser jp = new JSONParser();
			JSONObject root = (JSONObject)jp.parse(json);
			JSONArray arr = (JSONArray)root.get("items");
			for(int i=0;i<arr.size();i++) {
				JSONObject obj = (JSONObject)arr.get(i);
				String desc = (String)obj.get("description");
				list.add(desc);
			}
		}catch(Exception ex) {}
		return list;
	}
	/*public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("계절을 입력해 보세요(봄/여름/가을/겨울):");
		String fd = scan.next();
		String json = NaverDataClass.recommendData(fd);
//		List<String> jList = jsonParser(json);
//		for(String s:jList) {
//			System.out.println(s);
//		}
		FoodRecommendDAO dao = new FoodRecommendDAO();
		List<String> list = dao.foodAllData();
		
		try {
			Pattern[] p = new Pattern[list.size()];
			for(int i=0;i<p.length;i++) {
				//내가 찾는 문자열을 집어넣어 주는 것 = 패턴
				p[i] = Pattern.compile(list.get(i));
			}
			Matcher[] m = new Matcher[list.size()];
			List<RecommendVO> rList = new ArrayList<RecommendVO>();
			int[] count = new int[list.size()];
			for(String s:jList) {
				for(int i=0;i<m.length;i++) {
					m[i] = p[i].matcher(s);//p[i]가 m[i]에 포함되는지
					while(m[i].find()) {
						String ss = m[i].group();//실제 데이터를 가져옴
//						System.out.println(ss);
//						RecommendVO vo = new RecommendVO();
//						vo.setName(ss);
//						vo.setCount(vo.getCount()+1);
//						rList.add(vo);
						count[i]++;
					}
				}
			}
			//실제 추천할 데이터 출력
			for(int i=0;i<list.size();i++) {
				String name = list.get(i);
				if(count[i]>2) {
					System.out.println(name+":"+count[i]);
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}*/
}
