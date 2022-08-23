package doodoo.dev.auto;

import org.springframework.stereotype.Repository;

@Repository("ms")
public class MySQL implements DataBase{
	@Override
	public void getConnection() {
		System.out.println("MySQL 연결");
	}
	@Override
	public void disConnection() {
		System.out.println("MySQL 연결 해제");
	}
}