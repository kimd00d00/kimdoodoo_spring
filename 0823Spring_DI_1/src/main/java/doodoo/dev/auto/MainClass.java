package doodoo.dev.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("mc")
public class MainClass {
	@Autowired
	@Qualifier("ms")
	private DataBase db;//ms�� ora�� ��ϵ� �� ���� bean�� �ֱ� ������.. ���߿� ���� ��û�Ѱ��� @Qualifier�� ����� �־�� �Ѵ�.
	//Autowired�� ���� �� ���� ��.. Ư�� ��ü�� ������ �� ����.
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app2.xml");
		MainClass mc = (MainClass)app.getBean("mc");
		mc.db.getConnection();
		mc.db.disConnection();
	}
}
