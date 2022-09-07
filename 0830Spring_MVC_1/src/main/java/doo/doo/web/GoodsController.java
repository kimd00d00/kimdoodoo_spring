package doo.doo.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import doo.doo.dao.*;
@Controller
@RequestMapping("goods/") //53p 중복경로 제거
public class GoodsController {
	@Autowired
	private GoodsService service;
	
	@GetMapping("main.do")
	public String goods_main() {
		return "goods/main";
	}
	@GetMapping("goods_all.do")
	public String goods_all_list(String page, Model model) {
		//결과값 전송 객체 == Model!
		if(page==null)
			page = "1";
		int curPage = Integer.parseInt(page);
		Map map = new HashMap();
		map.put("start",(curPage*12)-11);
		map.put("end",curPage*12);
		
		int totalPage = service.goodsAllTotalPage();
		List<GoodsVO> list = service.goodsAllListData(map);
		for(GoodsVO vo:list) {
			String name = vo.getName();
			if(name.length()>10) {
				name = name.substring(0,10)+"...";
				vo.setName(name);
			}else {
				vo.setName(name);
			}
		}
		
		final int BLOCK = 10;
		int startPage = ((curPage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curPage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalPage)
			endPage=totalPage;
		
		model.addAttribute("curPage",curPage);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		
		model.addAttribute("list",list);
		return "goods/goods_all";
	}
	@GetMapping("goods_all_detail.do")
	public String goods_all_detail(int no, Model model) {
		GoodsVO vo = service.goodsAllDetailData(no);
		model.addAttribute("vo", vo);
		return "goods/goods_all_detail";
	}
	
	//베스트 : 이전/다음
	@GetMapping("goods_new.do")
	public String goods_new_list(String page, Model model) {
		if(page==null)
			page = "1";
		int curPage = Integer.parseInt(page);
		int totalPage = service.goodsNewTotalPage();
		
		Map map = new HashMap();
		int rowSize = 12;
		int start = (rowSize*curPage) - (rowSize-1);
		int end = rowSize*curPage;
		map.put("start",start);
		map.put("end",end);
		
		List<GoodsVO> list = service.goodsNewListData(map);
		for(GoodsVO vo:list) {
			String name = vo.getName();
			if(name.length()>10) {
				name = name.substring(0,10)+"...";
				vo.setName(name);
			}else {
				vo.setName(name);
			}
		}
		model.addAttribute("curPage",curPage);
		model.addAttribute("totalPage",totalPage);
		
		model.addAttribute("list",list);
		
		return "goods/goods_new";
	}
	
	@GetMapping("goods_new_detail.do")
	public String goods_new_detail(int no, Model model) {
		GoodsVO vo = service.goodsNewDetailData(no);
		model.addAttribute("vo", vo);
		return "goods/goods_new_detail";
	}
}
