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
	private DataBase db;//ms와 ora로 등록된 두 개의 bean이 있기 때문에.. 둘중에 뭐를 요청한건지 @Qualifier로 명시해 주어야 한다.
	//Autowired는 같은 게 있을 때.. 특정 객체를 지정할 수 없다.
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app2.xml");
		MainClass mc = (MainClass)app.getBean("mc");
		mc.db.getConnection();
		mc.db.disConnection();
	}
}
