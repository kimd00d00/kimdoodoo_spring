package doo.doo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import doo.doo.mapper.BoardMapper;
import doo.doo.vo.BoardVO;

@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	/*@Select("SELECT no, subject, name, TO_CHAR(regdate,'yyyy-MM-dd') as dbday, hit, num "
			+ "FROM (no, subject, name, regdate, hit "
			+ "FROM (no, subject, name, regdate, hit "
			+ "FROM goods_board ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")*/
	public List<BoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	
	/*@Select("SELECT COUNT(*) FROM goods_board")*/
	public int boardRowCount() {
		return mapper.boardRowCount();
	}
	
	/*@Insert("INSERT INTO goods_board(no, name, subject, content, pwd) VALUES ("
			+ "(SELECT NVL(MAX(no)+1,1) FROM goods_board),"
			+ "#{name},#{subject},#{content},#{pwd})")*/
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	
	/*@Update("UPDATE goods_board SET hit=hit+1 WHERE no=#{no}")
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate,'yyyy-MM-dd') as dbday, hit, num "
			+ "FROM (SELECT no, name, subject, content, regdate, hit, rownum as num "
			+ "FROM (SELECT no, name, subject, content, regdate, hit "
			+ "FROM goods_board ORDER BY no DESC)) "
			+ "WHERE no=#{no}")*/
	public BoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	/*@Select("SELECT no, subject, num "
			+ "FROM (SELECT no, subject, rownum as num "
			+ "FROM (SELECT no, subject "
			+ "FROM goods_board ORDER BY no DESC)) "
			+ "WHERE num=#{num}")*/
	public BoardVO boardPNData(int num) { //prev, next Data
		return mapper.boardPNData(num);
	}
	
	/*@Select("SELECT no, subject, hit, rownum "
			+ "FROM (SELECT no, subject, hit "
			+ "FROM goods_board ORDER BY hit DESC) "
			+ "WHERE rownum<=5")*/
	public List<BoardVO> boardFooterData(){
		return mapper.boardFooterData();
	}
}
