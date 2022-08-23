package doodoo.dev.spring;

public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app = new ApplicationContext("app.xml");
		Emp e = (Emp)app.getBean("e");
		e.print();
	}

}
