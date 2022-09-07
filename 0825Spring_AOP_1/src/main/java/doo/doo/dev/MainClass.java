package doo.doo.dev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		MovieDAO dao = (MovieDAO)app.getBean("dao");
//		dao.movieDetailData();
//		dao.movieFindData();
		
		MusicDAO dao1 = (MusicDAO)app.getBean("dao1");
		dao1.musicDetailData();
	}

}
