package doo.doo.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import doo.doo.dao.*;
// Model => DispatcherServlet���κ��� ������ �޾Ƽ� ��ûó��
@Controller
public class MainController {
    @Autowired //���������� ������ ��ü�� �ڵ����� �ּҰ��� ��� �´� 
    private GoodsDAO dao;
    
    // ��û�� ���� => �䱸���� �м� => ���� 
    @GetMapping("main/main.do") // if => index , if�� �߰� 
    public String main_main(String page,Model model)
    {
    	CommonsController.commonsData("goods_all", page, model, dao);
    	//=========== main���� ȭ�� ���� 
    	model.addAttribute("main_jsp", "../goods/list.jsp");
    	model.addAttribute("t", "��ü��ǰ");
    	return "main/main";
    }
    
}
