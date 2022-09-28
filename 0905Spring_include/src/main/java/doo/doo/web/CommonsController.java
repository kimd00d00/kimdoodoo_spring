package doo.doo.web;

import java.util.*;

import org.springframework.ui.Model;
import doo.doo.dao.*;

public class CommonsController {
	public static void commonsData(String table_name, String page, GoodsDAO dao, Model model) {
		if(page==null)
			page = "1";
		int curPage = Integer.parseInt(page);
		int rowSize = 12;
		int start = rowSize*curPage - (rowSize-1);
		int end = rowSize*curPage;
		
		Map map = new HashMap();
		map.put("start",start);
		map.put("end",end);
		map.put("table_name",table_name);
		
		List<GoodsVO> list = dao.goodsListData(map);
		//글자수 자르기
		for(GoodsVO vo:list) {
			String name = vo.getGoods_name();
			if(name.length()>20) {
				name = name.substring(0,20)+"...";
				vo.setGoods_name(name);
			}
			vo.setGoods_name(name);
		}
		int totalPage = dao.goodsTotalPage(map);
		
		model.addAttribute("curPage",curPage);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("list",list);
	}
	public static void commonsDetailData(String table_name, int no, GoodsDAO dao, Model model) {
		Map map = new HashMap();
		map.put("table_name",table_name);
		map.put("no",no);
		GoodsVO vo = dao.goodsDetailData(map);
		model.addAttribute("vo", vo);
	}
}
