package doo.doo.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import doo.doo.dao.SeoulDAO;
import doo.doo.vo.*;
@RestController
public class SeoulRestController {
	
	@Autowired
	private SeoulDAO sDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping(value="seoul/list_vue.do", produces="text/plain;charset=UTF-8")
	public String seoul_list_vue(String page, String type) {
		if(page==null)
			page="1";
		if(type==null)
			type="1";
		int index=Integer.parseInt(type);
		String table_name="";
		String[] tables = {"","seoul_location","seoul_nature","seoul_shop"};
		
		int curPage = Integer.parseInt(page);
		Map map = new HashMap();
		int rowSize = 12;
		int start = curPage*rowSize - (rowSize-1);
		int end = curPage*rowSize;
		
		map.put("start",start);
		map.put("end",end);
		map.put("table_name",tables[index]);
		
		List<SeoulVO> list = sDao.seoulListData(map);
		int totalPage = sDao.seoulTotalPage(map);
		
		String result = "";
		JSONArray arr = new JSONArray();
		int k=0;
		for(SeoulVO vo:list) {
			JSONObject obj = new JSONObject();
			obj.put("no",vo.getNo());
			obj.put("title",vo.getTitle());
			obj.put("poster",vo.getPoster());
			if(k==0) {
				obj.put("curPage",curPage);
				obj.put("totalPage",totalPage);
				obj.put("type",index);
			}
			arr.add(obj);
			k++;
		}
		result = arr.toJSONString();
		return result;
	}
	
	@GetMapping(value="seoul/detail_vue.do", produces="text/plain;charset=UTF-8")
	public String seoul_detail_vue(int no, int type) {
		String result = "";
		try {
			String[] table_name = {"","seoul_location","seoul_nature","seoul_shop"};
			Map map = new HashMap();
			map.put("table_name",table_name[type]);
			map.put("no",no);
			
			SeoulVO vo = sDao.seoulDetailData(map);
			
			JSONObject obj = new JSONObject();
			obj.put("no",vo.getNo());
			obj.put("title",vo.getTitle());
			obj.put("address",vo.getAddress().substring(vo.getAddress().indexOf(" ")).trim());
			obj.put("poster",vo.getPoster());
			obj.put("msg",vo.getMsg());
			
			result = obj.toJSONString();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	@GetMapping(value="seoul/cook_list.do", produces="text/plain;charset=UTF-8")
	public String seoul_cook_list(String type, HttpServletRequest request) {
		if(type==null)
			type="1";
		int t = Integer.parseInt(type);
		String[] cook_name = {"","location","nature","shop"};
		String result = "";
		Cookie[] cookies = request.getCookies();
		List<SeoulVO> list = new ArrayList<SeoulVO>();
		if(cookies!=null) {
			for(int i=cookies.length-1; i>=0; i--) {
				if(cookies[i].getName().startsWith(cook_name[t])) {
					Map map = new HashMap();
					String no = cookies[i].getValue();
					map.put("no",no);
					map.put("table_name","seoul_"+cook_name[t]);
					SeoulVO vo = sDao.seoulDetailData(map);
					list.add(vo);
				}
			}
		}
		//list -> JSON
		JSONArray arr = new JSONArray();
		for(SeoulVO vo:list) {
			JSONObject obj = new JSONObject();
			obj.put("no",vo.getNo());
			obj.put("title",vo.getTitle());
			obj.put("address",vo.getAddress().substring(vo.getAddress().indexOf(" ")).trim());
			obj.put("poster",vo.getPoster());
			obj.put("msg",vo.getMsg());
			arr.add(obj);
		}
		result = arr.toJSONString();
		return result;
	}
	
	@GetMapping(value="member/login_vue.do", produces="text/plain;charset=UTF-8")
	public String seoul_login_vue(String id, String pwd, HttpSession session) {
		String result = "";
		String tmp = encoder.encode(pwd);//비밀번호 암호화
		System.out.println("실제pwd:"+pwd+","+"암호화된 pwd:"+tmp);
		MemberVO vo = sDao.isLogin(id, pwd);
		if(vo.getMsg().equals("OK")) {
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			//session은 기본이 30분 유지
		}
		result = vo.getMsg();
		return result;
	}
}
