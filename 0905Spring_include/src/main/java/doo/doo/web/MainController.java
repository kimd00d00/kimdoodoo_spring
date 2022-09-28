package doo.doo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import doo.doo.dao.*;

@Controller //DispatcherServlet���κ��� ������ �޾� ��û ó��
public class MainController {
	@Autowired //���������� ������ ��ü�� �ּҰ��� �ڵ����� �ο��Ѵ�
	private GoodsDAO dao;
	
	//��û�� ������ ���� �䱸������ �м��ؼ� ������ �ش�
	@GetMapping("main/main.do")
	public String main_main(String page, Model model) {
		CommonsController.commonsData("goods_all", page, dao, model);//������...
		model.addAttribute("main_jsp","../goods/home.jsp");
		return "main/main";
	}
	
}
