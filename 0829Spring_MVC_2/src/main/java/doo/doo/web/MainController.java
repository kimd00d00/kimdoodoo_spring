package doo.doo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
/*
 *  �Ű������� � ���� �Ѿ�����Ŀ� ���� �� �޶�����.
 *  �Ű������� �� �� �ִ� �� : ���尴ü�� �� ��� ����. request, response, session, application, config, out
 *  	�� �ܿ� ����ڰ� ������ ���� �޾ƿ� �� �ִ�. �� ���� ������ �����µ� VO����(Ŀ�ǵ� ��ü), ������ �� ������������ �޴� ���.
 *  	������ ����, request�� ������� �ʴ� ��쿡�� Model�� ���� ��ü�� �̿��Ѵ�.
 *  ������ : String, void
 *  	String�� 90%, ���� �޾� ����ϴ� JSP�� ������ �� : forward
 *  	������ : redirect:~ ó�� ������ �� : sendRedirect
 *  	void : ajax, �ٿ�ε�, axios(Vue,React)... 
 *  application-context.xml�� ���������� ������  Spring�� .do�� �� ��� ã������ �� �� ����.
 */
@Controller
public class MainController {
	@GetMapping("main/input")
	public String main_input() {
		return "main/input";
	}
	@GetMapping("main/output.do")
	public String main_output(int no, String name, double avg, boolean isadmin, Model model) {
		model.addAttribute("no",no);
		model.addAttribute("name",name);
		model.addAttribute("avg",avg);
		model.addAttribute("isadmin",isadmin);
		
		return "main/output";
	}
	@PostMapping("main/output1.do")//post�� ���������ϱ� PostMapping ���� �޾ƾ� ��!!
	//405 ���� - ������� �ʴ� �޼���
	public String main_output1(String id, String pwd, Model model) {
		
		return "main/output1";
	}
}
