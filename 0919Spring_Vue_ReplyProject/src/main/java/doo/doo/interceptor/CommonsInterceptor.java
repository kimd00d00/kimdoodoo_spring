package doo.doo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import java.util.*;
import java.text.*;

public class CommonsInterceptor extends HandlerInterceptorAdapter{

	@Override //Controller 메서드 수행 전에 호출됨 == 자동 로그인에 쓰인다
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String today=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		System.out.println("=================");
		System.out.println("요청 시간:"+today);
		System.out.println("1.클라이언트 요청");
		System.out.println("2.클라이언트가 어떤 경로(URI)를 요청했는지 확인:"+request.getRequestURI());
		System.out.println("3.클라이언트 요청 처리 준비 완료!");
		//@GetMapping등 어노테이션 아래에 있는 메서드 호출
		return super.preHandle(request, response, handler);
	}

	@Override //Controller 메서드 수행 완료시 호출됨 == 권한 설정에 쓰인다
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String today=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		System.out.println("=================");
		System.out.println("요청 처리 완료:"+today);
		System.out.println("1.@GetMapping,@PostMapping,@RequestMapping 어노테이션 찾기 by.HandlerMapping");
		System.out.println("2.클라이언트 요청 처리 완료!");
//		System.out.println("3.결과값을 JSP로 전송! 어떤 JSP로 보냈냐면...:"+modelAndView.getViewName().toString());
		System.out.println("4.전송 준비 완료");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override //
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String today=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		System.out.println("=================");
		System.out.println("요청 데이터 전송:"+today);
		System.out.println("1.출력에 필요한 데이터를 JSP로  전송 완료!");
		System.out.println("2.JSP 화면 출력!");
		super.afterCompletion(request, response, handler, ex);
	}
	
}
