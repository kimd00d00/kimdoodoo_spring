package doo.doo.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import doo.doo.manager.NewsManager;
import doo.doo.vo.NewsVO;

import java.util.*;
@Controller
public class NewsController {
	@Autowired
	private NewsManager mgr;
	/*
	{
    "lastBuildDate": "Wed, 28 Sep 2022 09:39:15 +0900",
    "total": 8525216,
    "start": 1,
    "display": 100,
    "items": [{
        "title": "29CM, 대구 출격…‘이구갤러리 대구’ 30일 개점",
        "originallink": "https:\/\/www.viva100.com\/main\/view.php?key=20220928010007013",
        "link": "https:\/\/www.viva100.com\/main\/view.php?key=20220928010007013",
        "description": "매 월 하나의 메인 브랜드를 선정하여 수장고 내부에 전시하고... ",
        "pubDate": "Wed, 28 Sep 2022 09:38:00 +0900"
    }, {
        "title": "현대자동차 아이오닉 5, 워즈오토 &apos;최고 10대 엔진&apos; 수상...최고 수준 전동화...",
        "originallink": "http:\/\/www.pinpointnews.co.kr\/news\/articleView.html?idxno=145165",
        "link": "http:\/\/www.pinpointnews.co.kr\/news\/articleView.html?idxno=145165",
        "description": "또한 &apos;아우토 빌트 최고의 수입차 전기차 부문 1위...",
        "pubDate": "Wed, 28 Sep 2022 09:38:00 +0900"
    },...
    	]
    }
	 */
	@RequestMapping("news/find.do")
	public String news_find(String ss, Model model) {
		if(ss==null)
			ss="상품";
		String json = mgr.newsFind(ss);
		try {
			JSONParser jp = new JSONParser();
			JSONObject root = (JSONObject)jp.parse(json);
			JSONArray arr = (JSONArray)root.get("items");
			List<NewsVO> list = new ArrayList<NewsVO>();
			for(int i=0;i<arr.size();i++) {
				NewsVO vo = new NewsVO();
				JSONObject obj = (JSONObject)arr.get(i);
				vo.setTitle((String)obj.get("title"));
				vo.setLink((String)obj.get("link"));
				vo.setDescription((String)obj.get("description"));
				list.add(vo);
			}
			model.addAttribute("list",list);
		}catch(Exception ex) {}
		model.addAttribute("main_jsp","../news/find.jsp");
		return "main/main";
	}
}
