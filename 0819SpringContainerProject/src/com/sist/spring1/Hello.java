package com.sist.spring1;
//1. new를 사용하지 않는다.
//new를 사용하면 결합성이 높은 프로그래밍... 가급적 사용하지 않도록 한다.
//new말고는 뭘로? reflection -> Class.forName() 이렇게 하는거

public class Hello {
	public void SayHello(String name) {
		System.out.println(name+"님 안녕하세요~");
	}
}
