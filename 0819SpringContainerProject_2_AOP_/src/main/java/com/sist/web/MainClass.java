package com.sist.web;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao = app.getBean("dao",EmpDAO.class);
		EmpDAO dao1 = app.getBean("dao",EmpDAO.class);
		EmpDAO dao2 = app.getBean("dao",EmpDAO.class);
		List<EmpVO> list = dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEname());
		}
	}

}
