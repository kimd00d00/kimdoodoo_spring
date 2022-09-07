package doo.doo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //모델이라는걸 알려줌
@RequestMapping("main/") //경로명의 중복을 제거한다. 이제 main/input.do 이렇게 써줄 필요가 없어짐.
public class MainController {
	@RequestMapping("input.do")
	public String main_input(HttpServletRequest request, HttpServletResponse response) {
		return "main/input";
	}
	@RequestMapping("output.do")
	public String main_output(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String loc = request.getParameter("loc");
		String content = request.getParameter("content");
		String[] hobby = request.getParameterValues("hobby");
		
		request.setAttribute("name", name);
		request.setAttribute("sex", sex);
		request.setAttribute("loc", loc);
		request.setAttribute("content", content);
		request.setAttribute("hobby", hobby);
		return "main/output"; //.jsp를 붙이지 않음!!!
	}
	@RequestMapping("output1.do")
	public String main_output1(String name, String sex, String loc, String content, String[] hobby, Model model) {
		//Model : 데이터 전송 객체
		//request 없이 매개변수로 받아 Model을 통해 데이터를 전송하고 구현해 보자.
		model.addAttribute("name",name); //=request.setAttribute("name",name);
		model.addAttribute("sex",sex);
		model.addAttribute("loc",loc);
		model.addAttribute("content",content);
		model.addAttribute("hobby",hobby);
		
		return "main/output";
	}
	
	@RequestMapping("output2.do")
	public String main_output2(MemberVO vo, Model model) {
		model.addAttribute("vo",vo);
		return "main/output1";
	}
}
