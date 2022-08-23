package doodoo.dev.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app1.xml");
		Member mem1 = (Member)app.getBean("mem1");
		mem1.print();
		Member mem2 = app.getBean("mem2",Member.class);
		mem2.print();
		Member mem3 = (Member)app.getBean("mem3");
		mem3.print();
	}
}
