package doodoo.dev.di;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;

public class Member implements BeanFactoryAware, InitializingBean{
	private int mno;
	private String name;
	public Member() {
		System.out.println("객체 생성");
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
		System.out.println("setter DI수행");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("setter DI수행");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("SetXxx() => DI 완료");
	}
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("setBeanFactory() : 클래스를 저장 관리할 클래스 호출");
	}
	
	//여기부터 프로그래머의 영역
	public void display() {
		System.out.println("프로그래머가 호출");
	}
	/*
	 * 1. XML읽기
	 * 2. XMl 파서
	 *  2-1. <bean>에 등록된 모든 클래스 메모리 할당
	 *  2-2. setter DI
	 *  2-3. init-method에 등록된 메서드 호출
	 * --------------------------------
	 * 3. 사용자 메서드 호출
	 * --------------------------------
	 * 4. 스프링에서 메모리 해제
	 */
	
}
