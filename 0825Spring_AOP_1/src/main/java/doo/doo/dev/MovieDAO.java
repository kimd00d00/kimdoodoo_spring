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
		System.out.println("영화 목록 가지고 오기");
		db.disConnection();
	}
	public void movieDetailData() {
		db.getConnection();
		System.out.println("영화 상세 정보 가지고 오기");
		db.disConnection();
	}
	public void movieFindData() {
		db.getConnection();
		System.out.println("영화 검색 데이터 가지고 오기");
		db.disConnection();
	}
}
