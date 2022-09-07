package doo.doo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import doo.doo.mapper.BoardMapper;

@Repository
public class BoardDAO {
	//인터페이스를 구현한 클래스의 주소값을 스프링에 요청
	@Autowired
	private BoardMapper mapper;
	//글목록 불러오기
	public List<BoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	//글 전체 페이지수 불러오기
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	//글쓰기
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	
	//상세보기
	public BoardVO boardDetailData(int no) {
		mapper.boardHitIncrement(no); //조회수 증가
		return mapper.boardDetailData(no);
	}
	
	//글 삭제하기 화면
	public BoardVO boardDeleteData(int no) {
		return mapper.boardDetailData(no);
	}
	//글 삭제하기
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
	
	//글 수정하기 화면
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	//글 수정하기-비밀번호 체크
	public String boardGetPwd(int no) {
		return mapper.boardGetPwd(no);
	}
	//글 수정하기 처리
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
	
	//글 찾기
	public List<BoardVO> boardFindData(Map map){
		return mapper.boardFindData(map);
	}
}
