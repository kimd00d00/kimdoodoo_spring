package doo.doo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import doo.doo.mapper.BoardMapper;

@Repository
public class BoardDAO {
	//�������̽��� ������ Ŭ������ �ּҰ��� �������� ��û
	@Autowired
	private BoardMapper mapper;
	//�۸�� �ҷ�����
	public List<BoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	//�� ��ü �������� �ҷ�����
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	//�۾���
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	
	//�󼼺���
	public BoardVO boardDetailData(int no) {
		mapper.boardHitIncrement(no); //��ȸ�� ����
		return mapper.boardDetailData(no);
	}
	
	//�� �����ϱ� ȭ��
	public BoardVO boardDeleteData(int no) {
		return mapper.boardDetailData(no);
	}
	//�� �����ϱ�
	public boolean boardDelete(int no, String pwd) {
		boolean bCheck = false;
		String db_pwd = mapper.boardGetPwd(no);
		if(db_pwd.equals(pwd)) {
			bCheck=true;
			mapper.boardDelete(no);
		}else {
			bCheck=false;
		}
		return bCheck;
	}
	
	//�� �����ϱ� ȭ��
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	//�� �����ϱ�-��й�ȣ üũ
	public String boardGetPwd(int no) {
		return mapper.boardGetPwd(no);
	}
	//�� �����ϱ� ó��
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck = false;
		String db_pwd = mapper.boardGetPwd(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck=true;
			mapper.boardUpdate(vo);
		}else {
			bCheck=false;
		}
		return bCheck;
	}
	
	//�� ã��
	public List<BoardVO> boardFindData(Map map){
		return mapper.boardFindData(map);
	}
}
