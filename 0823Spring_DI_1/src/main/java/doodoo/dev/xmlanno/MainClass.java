package doodoo.dev.xmlanno;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
@Component("mc")
public class MainClass {
	@Autowired
	private MovieDAO dao;
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app3.xml");
		Map map = new HashMap();
		map.put("start",1);
		map.put("end",20);
		//스프링에서 받아올 건지(Autowired) 우리가 직접 달라고 할 건지 정해야 함
		MainClass mc = (MainClass)app.getBean("mc");
//		MovieDAO dao = (MovieDAO)app.getBean("movieDAO"); //@Repository안에 id가 부여되어 있지 않음 -> 디폴트 아이디 = 맨앞글자 소문자
		List<MovieVO> list = mc.dao.movieListData(map);
		for(MovieVO vo:list) {
			System.out.println(vo.getMno()+"."+vo.getTitle());
		}
	}
}
