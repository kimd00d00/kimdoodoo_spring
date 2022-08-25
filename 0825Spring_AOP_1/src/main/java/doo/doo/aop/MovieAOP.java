package doo.doo.aop;

public class MovieAOP {
	public void getConnection() {
		System.out.println("Oracle connect...");
	}
	public void disConnection() {
		System.out.println("Oracle disconnect...");
	}
}
