package doodoo.dev.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//1. �����̳ʿ� XML������ ���� -> XML�Ľ� -> �޸� �Ҵ� -> setter�� �� ����
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		EmpVO e1 = (EmpVO)app.getBean("e1"); //� Ŭ�������� �����ϴ��� �ּҰ��� �����ϴ�
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
