package doo.doo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
/*
 *  매개변수로 어떤 값이 넘어오느냐에 따라 다 달라진다.
 *  매개변수로 쓸 수 있는 것 : 내장객체는 다 사용 가능. request, response, session, application, config, out
 *  	그 외에 사용자가 보내준 값도 받아올 수 있다. 두 가지 정도로 나뉘는데 VO단위(커맨드 객체), 낱개로 각 데이터형으로 받는 방법.
 *  	순서는 없고, request를 사용하지 않는 경우에는 Model을 전송 객체로 이용한다.
 *  리턴형 : String, void
 *  	String이 90%, 값을 받아 출력하는 JSP를 설정할 때 : forward
 *  	재전송 : redirect:~ 처럼 보내는 것 : sendRedirect
 *  	void : ajax, 다운로드, axios(Vue,React)... 
 *  application-context.xml을 설정해주지 않으면  Spring이 .do를 뭐 어떻게 찾을지를 알 수 없다.
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
	@PostMapping("main/output1.do")//post로 보내줬으니까 PostMapping 으로 받아야 함!!
	//405 에러 - 허용하지 않는 메서드
	public String main_output1(String id, String pwd, Model model) {
		
		return "main/output1";
	}
}
