package doodoo.dev.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//1.XMl 파일 전송
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		Member mem = (Member)app.getBean("mem");
		mem.display();
		//2.
	}

}
