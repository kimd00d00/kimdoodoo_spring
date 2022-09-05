package doo.doo.dao;

import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import doo.doo.mapper.BoardMapper;

@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	public int boardCount() {
		return mapper.boardCount();
	}
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	public void hitIncrement(int no) {
		mapper.hitIncrement(no);
	}
	public BoardVO boardDetailData(int no) {
		return mapper.boardDetailData(no);
	}
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)//실행할 때마다 조립해 달라
	public void boardReplyInsert(int pno, BoardVO vo) {
		BoardVO pvo = mapper.boardParentInfoData(pno);
		mapper.boardGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pno);
		mapper.boardReplyInsert(vo);
		mapper.depthIncrement(pno);
	}
	
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck = false;
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck = true;
			mapper.boardUpdate(vo);
		}
		return bCheck;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean boardDelete(int no, String pwd) {
		boolean bCheck = false;
		String db_pwd = mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck = true;
			BoardVO vo = mapper.boardGetRootDepthData(no);
			if(vo.getDepth()==0) {
				mapper.boardDelete(no);
			}else {
				vo.setContent("관리자가 삭제한 게시물입니다.");
				vo.setSubject("관리자가 삭제한 게시물입니다.");
				vo.setNo(no);
				mapper.boardDelete2(vo);
			}
			mapper.depthDecrement(vo.getRoot());
		}
		return bCheck;
	}
}
