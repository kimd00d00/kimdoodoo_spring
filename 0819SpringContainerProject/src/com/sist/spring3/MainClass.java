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
		System.out.println("a2="+a2); //�ٸ� Ŭ�������� ���� �ᵵ ��� ���� ��ü�� �ȴ�. (�̱���)
		
		
		B b = (B)c.getBean("b");
		b.display();
		
		C cc = (C)c.getBean("c");
		cc.display();
	}

}
