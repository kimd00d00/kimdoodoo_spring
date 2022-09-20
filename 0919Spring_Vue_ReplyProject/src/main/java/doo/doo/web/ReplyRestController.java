package doo.doo.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.HttpSession;

import doo.doo.vo.*;
import doo.doo.dao.*;

@RestController
public class ReplyRestController {
	@Autowired
	private ReplyDAO dao;
	
	public String reply_json_data(List<ReplyVO> list, String id) {
		JSONArray arr = new JSONArray();
		int k=0;
		for(ReplyVO rvo:list) {
			JSONObject obj = new JSONObject();
			obj.put("no",rvo.getNo());
			obj.put("cno",rvo.getCno());
			obj.put("type",rvo.getType());
			obj.put("id",rvo.getId());
			obj.put("name",rvo.getName());
			obj.put("msg",rvo.getMsg());
			obj.put("dbday",rvo.getDbday());
			if(k==0) {
				obj.put("sessionId",id);
			}
			k++;
			arr.add(obj);
		}
		return arr.toJSONString();
	}
	
	//댓글 목록
	@GetMapping(value="seoul/reply_list.do", produces="text/plain;charset=UTF-8")
	public String reply_list(int cno, int type, HttpSession session) {
		String id = (String)session.getAttribute("id");
		String result = "";
		ReplyVO vo = new ReplyVO();
		vo.setCno(cno);
		vo.setType(type+3);
		List<ReplyVO> list = dao.replyListData(vo);
		result = reply_json_data(list,id);
		return result;
	}
	
	//댓글 쓰기
	@GetMapping(value="seoul/reply_insert.do", produces="text/plain;charset=UTF-8")
	public String reply_insert(ReplyVO vo, HttpSession session) {
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		vo.setId(id);
		vo.setName(name);
		vo.setType(vo.getType()+3);
		dao.replyInsert(vo);
		
		List<ReplyVO> list = dao.replyListData(vo);
		String result = reply_json_data(list,id);
		
		return result;
	}
	
	//댓글 수정
	@PostMapping(value="seoul/reply_update.do", produces="text/plain;charset=UTF-8")
	public String reply_update(ReplyVO vo) {
		dao.replyUpdate(vo);
		String result = "<script> location.href=\"../seoul/detail.do?no="+vo.getNo()+"&type="+vo.getType()+"\";</script>";
		return result;
	}
	
	//댓글 삭제
	@GetMapping(value="seoul/reply_delete.do", produces="text/plain;charset=UTF-8")
	public String reply_delete(ReplyVO vo, HttpSession session) {
		String result = "";
		String id = (String)session.getAttribute("id");
		//삭제 처리
		dao.replyDelete(vo.getNo());
		//삭제 후 목록 가져오기
		vo.setType(vo.getType()+3);
		List<ReplyVO> list = dao.replyListData(vo);
		result = reply_json_data(list,id);
		return result;
	}
}
