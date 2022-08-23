package doodoo.dev.di;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;

public class Member implements BeanFactoryAware, InitializingBean{
	private int mno;
	private String name;
	public Member() {
		System.out.println("��ü ����");
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
		System.out.println("setter DI����");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("setter DI����");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("SetXxx() => DI �Ϸ�");
	}
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("setBeanFactory() : Ŭ������ ���� ������ Ŭ���� ȣ��");
	}
	
	//������� ���α׷����� ����
	public void display() {
		System.out.println("���α׷��Ӱ� ȣ��");
	}
	/*
	 * 1. XML�б�
	 * 2. XMl �ļ�
	 *  2-1. <bean>�� ��ϵ� ��� Ŭ���� �޸� �Ҵ�
	 *  2-2. setter DI
	 *  2-3. init-method�� ��ϵ� �޼��� ȣ��
	 * --------------------------------
	 * 3. ����� �޼��� ȣ��
	 * --------------------------------
	 * 4. ���������� �޸� ����
	 */
	
}
