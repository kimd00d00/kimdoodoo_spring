package doo.doo.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter{

	@Override //Controller 메서드 수행 전에 호출됨 == 자동 로그인에 쓰인다
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//로그인하는순간 쿠키에 저장된 아이디와 이름이 있는지 확인해야 한다.
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				String key = cookies[i].getName();
				if(key.equals("id")) {
					String id = cookies[i].getValue();
					request.setAttribute("id", id);
					break;
				}
			}
		}
		/*HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("id");
		
		if(sid!=null) {//로그인 된 상태
			session.invalidate();
		}
		
		Cookie[] cookies = request.getCookies();
		String id = "", name = "", role = "";
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				String tmp = cookies[i].getName();
				if(tmp!=null) {
					if(cookies[i].getName().equals("id")) {
						id = cookies[i].getValue();
					}
					if(cookies[i].getName().equals("name")) {
						name = cookies[i].getValue();
					}
					if(cookies[i].getName().equals("role")) {
						role = cookies[i].getValue();
					}
					session.setAttribute("id", id);
					session.setAttribute("name", name);
					session.setAttribute("role", role);
				}
			}
		}*/
		return super.preHandle(request, response, handler);
	}

	@Override //Controller 메서드 수행 완료시 호출됨 == 권한 설정에 쓰인다
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override //
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		super.afterCompletion(request, response, handler, ex);
	}
}
