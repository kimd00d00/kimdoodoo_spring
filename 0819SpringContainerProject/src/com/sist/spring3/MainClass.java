package com.sist.spring3;

public class MainClass {

	public static void main(String[] args) {
		Container c = new Container();
		A a = (A)c.getBean("a");
		a.display();
		System.out.println("a="+a);
		A a1 = (A)c.getBean("a");
		System.out.println("a1="+a1);
		A a2 = (A)c.getBean("a");
		System.out.println("a2="+a2); //다른 클래스에서 갖다 써도 모두 같은 객체가 된다. (싱글톤)
		
		
		B b = (B)c.getBean("b");
		b.display();
		
		C cc = (C)c.getBean("c");
		cc.display();
	}

}
