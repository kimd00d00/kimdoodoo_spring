package doodoo.dev.auto;

import org.springframework.stereotype.Repository;

@Repository("ora")
public class Oracle implements DataBase{
	@Override
	public void getConnection() {
		System.out.println("Oracle ����");
	}
	@Override
	public void disConnection() {
		System.out.println("Oracle ���� ����");
	}
}