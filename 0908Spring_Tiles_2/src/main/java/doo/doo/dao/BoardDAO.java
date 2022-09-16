package doo.doo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import doo.doo.mapper.BoardMapper;
import doo.doo.vo.BoardVO;
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
	
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	
	public BoardVO boardDetailData(int no) {
		mapper.boardHitIncrement(no);
		return mapper.boardDetailData(no);
	}
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	public String boardUpdate(BoardVO vo) {
		String result="no";
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			result="yes";
			mapper.boardUpdate(vo);
		}
		return result;
	}
	public String boardDelete(String pwd, int no) {
		String result="no";
		String db_pwd = mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			result="yes";
			mapper.boardDelete(no);;
		}
		return result;
	}
}
