package doodoo.dev.anno;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
@Component("mc")
public class MainClass {
	@Autowired
	private MovieDAO dao; //�������� ������ dao�� �ּҰ��� ������ �ش�(���������� getBean() ��� ó����)
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app1.xml");
		MainClass mc = (MainClass)app.getBean("mc");
		Map map = new HashMap();
		map.put("start", 1);
		map.put("end",10);
		List<MovieVO> list = mc.dao.movieListData(map);
		for(MovieVO vo:list) {
			System.out.println(vo.getMno()+"."+vo.getTitle());
		}
		
	}
}
