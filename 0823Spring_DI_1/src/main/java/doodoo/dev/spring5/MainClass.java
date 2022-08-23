package doodoo.dev.spring5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class MainClass {
	@Autowired
	private MovieDAO dao;
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app 
			= new AnnotationConfigApplicationContext(MovieConfig.class);
//		MovieDAO dao = app.getBean("movieDAO",MovieDAO.class); //ID�������� �ʾ����� ����Ʈ
		//getBean("ID",Ŭ������) => ����ȯ �������� �� �� �ʿ� ����.
		Map map = new HashMap();
		map.put("start",1);
		map.put("end",100);
		MainClass mc = (MainClass)app.getBean("mainClass");
		List<MovieVO> list = mc.dao.movieListData(map);
		for(MovieVO vo:list) {
			System.out.println(vo.getMno()+"."+vo.getTitle());
		}
	}
}
