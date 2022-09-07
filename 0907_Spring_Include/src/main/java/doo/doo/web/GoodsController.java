package doo.doo.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import doo.doo.dao.*;
@Controller
public class GoodsController {
  @Autowired
  private GoodsDAO dao;
  @Autowired
  private ReplyDAO rdao;
  
  @GetMapping("goods/best.do")
  public String goods_best(String page,Model model)
  {
	  CommonsController.commonsData("goods_best", page, model, dao);
	  model.addAttribute("main_jsp", "../goods/list.jsp");
	  model.addAttribute("t", "베스트 상품");
	  model.addAttribute("table_name", "best");
	  return "main/main";
  }
  
  @GetMapping("goods/special.do")
  public String goods_special(String page,Model model)
  {
	  CommonsController.commonsData("goods_special", page, model, dao);
	  model.addAttribute("main_jsp", "../goods/list.jsp");
	  model.addAttribute("t", "특가 상품");
	  model.addAttribute("table_name", "special");
	  return "main/main";
  }
  
  @GetMapping("goods/new.do")
  public String goods_new(String page,Model model)
  {
	  CommonsController.commonsData("goods_new", page, model, dao);
	  model.addAttribute("main_jsp", "../goods/list.jsp");
	  model.addAttribute("t", "신상품");
	  model.addAttribute("table_name", "new");
	  return "main/main";
  }
  
  @GetMapping("goods/all_detail.do")
  public String all_detail(int no,Model model)
  {
	  CommonsController.goodsDetailData("goods_all", no, model, dao);
	  model.addAttribute("main_jsp", "../goods/detail.jsp");
	  //댓글 출력
	  ReplyVO vo = new ReplyVO();
	  vo.setNo(no);
	  vo.setType(1);
	  List<ReplyVO> rList = rdao.replyListData(vo);
	  model.addAttribute("rList", rList);
	  model.addAttribute("typeno", vo.getType());
	  return "main/main";
  }
  
  @GetMapping("goods/new_detail.do")
  public String new_detail(int no,Model model)
  {
	  CommonsController.goodsDetailData("goods_new", no, model, dao);
	  model.addAttribute("main_jsp", "../goods/detail.jsp");
	  //댓글 출력
	  ReplyVO vo = new ReplyVO();
	  vo.setBno(no);
	  vo.setType(2);
	  List<ReplyVO> rList = rdao.replyListData(vo);
	  model.addAttribute("rList", rList);
	  model.addAttribute("typeno", vo.getType());
	  return "main/main";
  }
  
  @GetMapping("goods/special_detail.do")
  public String special_detail(int no,Model model)
  {
	  CommonsController.goodsDetailData("goods_special", no, model, dao);
	  model.addAttribute("main_jsp", "../goods/detail.jsp");
	  //댓글 출력
	  ReplyVO vo = new ReplyVO();
	  vo.setBno(no);
	  vo.setType(3);
	  List<ReplyVO> rList = rdao.replyListData(vo);
	  model.addAttribute("rList", rList);
	  model.addAttribute("typeno", vo.getType());
	  return "main/main";
  }

  @GetMapping("goods/best_detail.do")
  public String best_detail(int no,Model model)
  {
	  CommonsController.goodsDetailData("goods_best", no, model, dao);
	  model.addAttribute("main_jsp", "../goods/detail.jsp");
	  //댓글 출력
	  ReplyVO vo = new ReplyVO();
	  vo.setBno(no);
	  vo.setType(4);
	  List<ReplyVO> rList = rdao.replyListData(vo);
	  model.addAttribute("rList", rList);
	  model.addAttribute("typeno", vo.getType());
	  return "main/main";
  }
  
  @RequestMapping("goods/find.do") //POST, GET 둘 다 가능한 경우
  public String goods_find(String fs,String ss,Model model)
  {
	  if(fs!=null && ss!=null)
	  {
		  Map map=new HashMap();
		  map.put("table_name",fs);
		  map.put("ss", ss);
		  List<GoodsVO> list=dao.goodsFindData(map);
		  model.addAttribute("list", list);
	  }
	  model.addAttribute("main_jsp", "../goods/find.jsp");
	  return "main/main";
  }
}