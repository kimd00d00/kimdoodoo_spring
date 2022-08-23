package com.sist.spring;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app = new ApplicationContext();
		AModel a= (AModel)app.getBean("a");
		a.display();
		
	}
}
