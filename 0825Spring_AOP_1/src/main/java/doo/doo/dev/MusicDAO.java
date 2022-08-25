package doo.doo.dev;

public class MusicDAO {
	private DataBase db;
	public DataBase getDb() {
		return db;
	}
	public void setDb(DataBase db) {
		this.db = db;
	}
	
	public void musicListData() {
		db.getConnection();
		System.out.println("music 목록");
		db.disConnection();
	}
	public void musicDetailData() {
		db.getConnection();
		System.out.println("music 상세");
		db.disConnection();
	}
	public void musicFindData() {
		db.getConnection();
		System.out.println("music 찾기");
		db.disConnection();
	}
}
