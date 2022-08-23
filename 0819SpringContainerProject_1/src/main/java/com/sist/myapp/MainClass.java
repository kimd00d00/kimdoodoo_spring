package com.sist.myapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//XML ∆ƒΩÃ
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		AModel a = (AModel)app.getBean("a");
		AModel a1 = (AModel)app.getBean("a");
		AModel a2 = (AModel)app.getBean("a");
		a.display();
		System.out.println(a);//ΩÃ±€≈Ê¿”
		System.out.println(a1);
		System.out.println(a2);
		
		BModel b = (BModel)app.getBean("b");
		b.display();
		CModel c = (CModel)app.getBean("c");
		c.display();
	}

}
