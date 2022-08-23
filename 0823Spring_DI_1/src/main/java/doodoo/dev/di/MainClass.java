package doodoo.dev.di;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		//�갡 Spring������ �ҷ����� ����, MovieDAO�� ���� �ִ�.
		//���� MovieDAO dao = new MovieDAO() �̷��� ���� -> nullPointException �߻��Ѵ�.
		//������ �ȿ� �ִ� ��ü�� DAO�� ���� �ֱ� ������... new�δ� ssf���� ������ ���� ���� �����̱� ������ null��
		Scanner scan = new Scanner(System.in);
		System.out.print("������ �Է�:");
		int page = scan.nextInt();
		
		int rowSize = 10;
		int start = (rowSize*page)-(rowSize-1);
		int end = rowSize*page;
		Map map = new HashMap();
		map.put("start",start);
		map.put("end",end);
		
		MovieDAO dao = (MovieDAO)app.getBean("dao"); //�������� ���� �ִ� ��ü�� ��� ��!
		List<MovieVO> list = dao.movieListData(map);
		for(MovieVO vo:list) {
			System.out.println(vo.getMno()+"."+vo.getTitle());
		}
	}
}
