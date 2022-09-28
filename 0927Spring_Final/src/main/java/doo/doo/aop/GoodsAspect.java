package doo.doo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;
import doo.doo.vo.*;
import doo.doo.manager.*;
import doo.doo.dao.*;
import javax.servlet.http.HttpServletRequest;
@Component
@Aspect
public class GoodsAspect {
	@Autowired
	private NewsManager mgr;
	@Autowired
	private GoodsDAO dao;
	@Autowired
	private BoardDAO bDao;
	
	@Before("execution(* doo.doo.web.*Controller.*(..))")
	public void footer() {
		try {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			String json = mgr.newsFind("신상품");
			JSONParser jp = new JSONParser();
			JSONObject obj = (JSONObject)jp.parse(json);
			JSONArray arr = (JSONArray)obj.get("items");
			List<NewsVO> nList = new ArrayList<NewsVO>();
			for(int i=0; i<5; i++) {
				NewsVO vo = new NewsVO();
				JSONObject o = (JSONObject)arr.get(i);
				vo.setTitle((String)o.get("title"));
				vo.setLink((String)o.get("link"));
				nList.add(vo);
			}
			request.setAttribute("newsList", nList);
			
			//조회수 높은 제품 목록
			List<GoodsVO> footerList = dao.goodsFooterData();
			for(GoodsVO vo:footerList) {
				String name = vo.getGoods_name();
				if(name.length()>15) {
					name = name.substring(0,15)+"..";
					vo.setGoods_name(name);
				}
				vo.setGoods_name(name);
			}
			request.setAttribute("footerList", footerList);
			
			//조회수 높은 게시글 목록
			List<BoardVO> boardFooterList = bDao.boardFooterData();
			for(BoardVO vo:boardFooterList) {
				String sub = vo.getSubject();
				if(sub.length()>13) {
					sub = sub.substring(0,13)+"...";
					vo.setSubject(sub);
				}
				vo.setSubject(sub);
			}
			request.setAttribute("boardFooterList", boardFooterList);
		}catch(Exception ex) {}
	}
}
