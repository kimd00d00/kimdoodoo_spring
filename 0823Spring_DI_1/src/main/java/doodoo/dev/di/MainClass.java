package doodoo.dev.di;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		//얘가 Spring세팅을 불러오는 역할, MovieDAO를 갖고 있다.
		//이제 MovieDAO dao = new MovieDAO() 이렇게 안함 -> nullPointException 발생한다.
		//스프링 안에 있는 객체만 DAO를 갖고 있기 때문에... new로는 ssf등의 정보를 받지 못한 상태이기 때문에 null임
		Scanner scan = new Scanner(System.in);
		System.out.print("페이지 입력:");
		int page = scan.nextInt();
		
		int rowSize = 10;
		int start = (rowSize*page)-(rowSize-1);
		int end = rowSize*page;
		Map map = new HashMap();
		map.put("start",start);
		map.put("end",end);
		
		MovieDAO dao = (MovieDAO)app.getBean("dao"); //스프링이 갖고 있는 객체를 얻어 옴!
		List<MovieVO> list = dao.movieListData(map);
		for(MovieVO vo:list) {
			System.out.println(vo.getMno()+"."+vo.getTitle());
		}
	}
}
