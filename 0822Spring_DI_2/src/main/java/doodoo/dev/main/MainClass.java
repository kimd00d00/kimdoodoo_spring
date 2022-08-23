package doodoo.dev.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import doodoo.dev.dao.*;
public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao = (EmpDAO)app.getBean("dao");
		List<EmpVO> list = dao.empListData();
		for(EmpVO vo:list) {
//			System.out.println(vo.getEmpno()+" "+vo.getEname());
		}
		Scanner scan = new Scanner(System.in);
		System.out.print("사번 입력:");
		int empno=scan.nextInt();
		EmpVO vo = dao.empDetailData(empno);
		System.out.println("empno:"+vo.getEmpno());
		System.out.println("name:"+vo.getEname());
		System.out.println("job:"+vo.getJob());
		System.out.println("sal:"+vo.getSal());
	}

}