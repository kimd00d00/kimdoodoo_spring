package doodoo.dev.spring_methodDI;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		GenericXmlApplicationContext gc = new GenericXmlApplicationContext("app_student.xml");
		Student s = (Student)gc.getBean("student1");
		//s.init()
		s.print();
		gc.close(); //s.destory()
	}

}
