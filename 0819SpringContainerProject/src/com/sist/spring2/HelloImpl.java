package com.sist.spring2;

public class HelloImpl implements Hello{

	@Override
	public void sayHello() {
		System.out.println("인터페이스 이용");
		//메서드를 수정해도 다른 클래스에는 영향을 주지 않는다. = 결합성이 낮다
		//인터페이스의 단점 : 인터페이스를 수정하면 관련된 모든 클래스에 문제가 생길 수 있다.
	}
	
}
