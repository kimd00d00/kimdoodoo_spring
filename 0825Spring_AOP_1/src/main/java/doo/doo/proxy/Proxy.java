package doo.doo.proxy;

public class Proxy {
	private MovieDAO dao;
	public Proxy(MovieDAO dao) {
		this.dao = dao;
	}
	public void movieListData() {
		System.out.println("����Ŭ ����");
		dao.movieListData();
		System.out.println("����Ŭ ���� ����");
	}
}
