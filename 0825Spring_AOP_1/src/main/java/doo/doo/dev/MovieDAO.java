package doo.doo.dev;

public class MovieDAO {
	private DataBase db;
	public DataBase getDb() {
		return db;
	}
	public void setDb(DataBase db) {
		this.db = db;
	}
	
	public void movieListData() {
		db.getConnection();
		System.out.println("��ȭ ��� ������ ����");
		db.disConnection();
	}
	public void movieDetailData() {
		db.getConnection();
		System.out.println("��ȭ �� ���� ������ ����");
		db.disConnection();
	}
	public void movieFindData() {
		db.getConnection();
		System.out.println("��ȭ �˻� ������ ������ ����");
		db.disConnection();
	}
}
