package doodoo.dev.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		MovieManager m = (MovieManager)app.getBean("movieManager");
		Scanner scan = new Scanner(System.in);
		System.out.println("========MENU========");
		System.out.println("1.���� �ڽ����ǽ�");
		System.out.println("2.�ǽð� ������");
		System.out.println("3.�¼� ������");
		System.out.println("4.�¶��� �󿵰�");
		System.out.println("====================");
		System.out.print("�޴� ����:");
		int menu = scan.nextInt();
		List<MovieVO> list = m.movieListData(menu);
		int i = 1;
		for(MovieVO vo:list) {
			System.out.println(i+"."+vo.getTitle()+"|"+vo.getGenre()+"|"+vo.getGrade());
			i++;
		}
	}
}
