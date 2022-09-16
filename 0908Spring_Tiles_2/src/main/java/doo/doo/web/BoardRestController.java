package doo.doo.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import doo.doo.dao.*;
import doo.doo.vo.*;

@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping(value="board/list_vue.do",produces="text/plain;charset=UTF-8")
	public String board_list_vue(String page) {
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		Map map = new HashMap();
		int rowsize = 10;
		int start = rowsize*curpage-rowsize+1;
		int end = rowsize*curpage;
		
		map.put("start",start);
		map.put("end",end);
		
		List<BoardVO> list = dao.boardListData(map);
		int totalpage = dao.boardTotalPage();
		
		//Javascript로 데이터를 전송
		String result = "";
		try {
			JSONArray arr = new JSONArray();
			//[{no:"1",name:"kim",..},{...},..] 이렇게
			int k=0;
			for(BoardVO vo:list) {
				JSONObject obj = new JSONObject();//==VO
				//JSONObject에 값을 채워 JSONArray로 넘겨 주면 VO의 ArrayList와 동일하게 작동한다.
				obj.put("no",vo.getNo());
				obj.put("subject",vo.getSubject());
				obj.put("name",vo.getName());
				obj.put("dbday",vo.getDbday());
				obj.put("hit",vo.getHit());
				//맨 첫번째 객체에 페이지 정보를 담아 넘긴다.
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
	
	@GetMapping(value="board/insert_vue.do",produces="text/plain;charset=UTF-8")
	public String board_insert_vue(BoardVO vo) {
		dao.boardInsert(vo);
		return "OK";
	}
	@GetMapping(value="board/detail_vue.do", produces="text/plain;charset=UTF-8")
	public String board_detail_vue(int no) {
		String result = "";
		BoardVO vo = dao.boardDetailData(no);
		JSONObject obj = new JSONObject();
		obj.put("no",vo.getNo());
		obj.put("subject",vo.getSubject());
		obj.put("content",vo.getContent());
		obj.put("name",vo.getName());
		obj.put("dbday",vo.getDbday());
		obj.put("hit",vo.getHit());
		
		result = obj.toJSONString();
		return result;
	}
	@GetMapping(value="board/update_vue.do", produces="text/plain;charset=UTF-8")
	public String board_update_vue(int no) {
		String result = "";
		BoardVO vo = dao.boardUpdateData(no);
		JSONObject obj = new JSONObject();
		System.out.println(vo.getContent());
		obj.put("no",vo.getNo());
		obj.put("subject",vo.getSubject());
		obj.put("content",vo.getContent());
		obj.put("name",vo.getName());
		
		result = obj.toJSONString();
		return result;
	}
	@GetMapping(value="board/update_vue_ok.do", produces="text/plain;charset=UTF-8")
	public String board_update_vue_ok(BoardVO vo) {
		String result = dao.boardUpdate(vo);
		return result;
	}
	@GetMapping(value="board/delete_vue_ok.do", produces="text/plain;charset=UTF-8")
	public String board_delete_vue_ok(String pwd, int no) {
		String result = dao.boardDelete(pwd, no);
		return result;
	}
}
