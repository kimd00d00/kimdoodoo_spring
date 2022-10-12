package doo.doo.app2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;
import doo.doo.dao.*;

public class MainClass {
	
	public static void main(String[] args) {
		//
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		MemberDAO dao = (MemberDAO)app.getBean("memberDAO");
		List<MemberVO> list = dao.memberListData();
		for(MemberVO vo:list) {
			System.out.println(vo.getNo()+" "+vo.getName()+" "+vo.getSex()+" "+vo.getContent());
		}
	}

}
