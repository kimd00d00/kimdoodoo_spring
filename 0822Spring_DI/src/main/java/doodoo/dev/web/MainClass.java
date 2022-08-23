package doodoo.dev.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//1. 컨테이너에 XML파일을 전송 -> XML파싱 -> 메모리 할당 -> setter에 값 주입
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		EmpVO e1 = (EmpVO)app.getBean("e1"); //어떤 클래스에서 생성하더라도 주소값이 동일하다
		e1.print();
		EmpVO e2 = app.getBean("e2",EmpVO.class);
		e2.print();
		EmpVO e3 = (EmpVO)app.getBean("e3");
		e3.print();
		
		System.out.println(e1);
		System.out.println(e2);
		System.out.println(e3);
	}

}
