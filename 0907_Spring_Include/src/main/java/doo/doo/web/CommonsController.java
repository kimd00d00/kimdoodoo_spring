package doo.doo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import doo.doo.dao.*;
// ������ => �ڵ�ȭ ó�� (AOP)
// OOP => ���� ó�� => �޼ҵ� (�޼ҵ� ȣ��)
// ��� ���� => OOP 
public class CommonsController {
   public static void commonsData(String table_name,String page,Model model,GoodsDAO dao)
   {
	    if(page==null)
   		   page="1";
	   	int curpage=Integer.parseInt(page);
	   	Map map=new HashMap();
	   	int rowSize=12;
	   	int start=(rowSize*curpage)-(rowSize-1);
	   	int end=(rowSize*curpage);
	   	map.put("start", start);
	   	map.put("end", end);
	   	map.put("table_name", table_name); // goods_all , goods_best , goods_new ....
	   	// movie
	   	List<GoodsVO> list=dao.goodsListData(map);
	   	// ���ڼ� ���� 
	   	for(GoodsVO vo:list)
	   	{
	   		String name=vo.getGoods_name();
	   		if(name.length()>25)
	   		{
	   			name=name.substring(0,25)+"...";
	   			vo.setGoods_name(name);
	   		}
	   		vo.setGoods_name(name);
	   	}
	   	int totalpage=dao.goodsTotalPage(map);
	   	
	   	// home.jsp�� ����� �����͸� ���� 
	   	model.addAttribute("curpage", curpage);
	   	model.addAttribute("totalpage", totalpage);
	   	model.addAttribute("list", list);
   }
   public static void goodsDetailData(String table_name,int no,Model model,GoodsDAO dao)
   {
	   Map map=new HashMap();
	   map.put("table_name", table_name);
	   map.put("no", no);
	   GoodsVO vo=dao.goodsDetailData(map);
	   model.addAttribute("vo", vo);
   }
}