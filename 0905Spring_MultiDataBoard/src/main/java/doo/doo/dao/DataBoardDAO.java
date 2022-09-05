package doo.doo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import doo.doo.mapper.DataBoardMapper;

@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	
	public List<DataBoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	public void boardInsert(DataBoardVO vo) {
		mapper.boardInsert(vo);
	}
	public DataBoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	public DataBoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	
	public boolean boardUpdate(DataBoardVO vo) {
		boolean bCheck = false;
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck = true;
			mapper.boardUpdate(vo);
		}
		return bCheck;
	}
	
	
	public boolean boardDelete(int no, String pwd) {
		boolean bCheck = false;
		String db_pwd = mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck = true;
			mapper.boardDelete(no);
		}
		return bCheck;
	}
	
	public DataBoardVO boardInfoData(int no) {
		DataBoardVO vo = new DataBoardVO();
		
		return vo;
	}
}
