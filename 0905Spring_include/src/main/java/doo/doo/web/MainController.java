package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import doo.doo.dao.*;

@Controller //DispatcherServlet으로부터 위임을 받아 요청 처리
public class MainController {
	@Autowired //스프링에서 생성한 객체의 주소값을 자동으로 부여한다
	private GoodsDAO dao;
	
	//요청의 종류를 보고 요구사항을 분석해서 구분해 준다
	@GetMapping("main/main.do")
	public String main_main(String page, Model model) {
		CommonsController.commonsData("goods_all", page, dao, model);//ㄷㅐ박...
		model.addAttribute("main_jsp","../goods/home.jsp");
		return "main/main";
	}
	
}
