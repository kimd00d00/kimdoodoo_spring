package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import doo.doo.vo.*;
import doo.doo.dao.ReplyDAO;
import doo.doo.service.*;

@Controller
public class SeoulController {
	@Autowired
	private SeoulService service;
	@Autowired
	private ReplyDAO dao;
	private String[] tables = {"","seoul_location","seoul_nature","seoul_shop"};
	@GetMapping("seoul/list.do")
	public String seoul_list(String page, int tab, Model model) {
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		Map map = new HashMap();
		int rowsize = 12;
		int start = (rowsize*curpage) - (rowsize-1);
		int end = rowsize*curpage;
		map.put("start",start);
		map.put("end",end);
		map.put("table_name",tables[tab]);
		List<SeoulVO> list = service.seoulListData(map);
		int totalpage = service.seoulTotalPage(map);
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		return "seoul/"+tables[tab];
	}
	
	@GetMapping("seoul/detail.do")
	public String seoul_detail(int tab, int no, Model model) {
		Map map = new HashMap();
		map.put("table_name", tables[tab]);
		map.put("no",no);
		SeoulVO vo = service.seoulDetailData(map);
		model.addAttribute("vo",vo);
		model.addAttribute("tab",tab); //구분을 위해 또 전송해 준다
		
		//댓글 출력
		ReplyVO rvo = new ReplyVO();
		rvo.setCno(no);
		rvo.setType(tab);
		List<ReplyVO> list = dao.replyListData(rvo);
		model.addAttribute("list",list);
		return "seoul/detail";
	}
}
