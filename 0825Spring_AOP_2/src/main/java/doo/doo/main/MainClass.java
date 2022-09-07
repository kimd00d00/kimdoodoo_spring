package doo.doo.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import doo.doo.dao.*;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		MovieDAO dao = (MovieDAO)app.getBean("dao");
		List<MovieVO> list = dao.movieListData();
		/*for(MovieVO vo:list) {
			System.out.println(vo.getMno()+"|"+vo.getTitle()+"|"+vo.getGenre());
		}*/
		
	}
}
