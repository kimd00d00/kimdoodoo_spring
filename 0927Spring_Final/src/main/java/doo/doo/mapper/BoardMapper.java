package doo.doo.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import doo.doo.vo.*;

public interface BoardMapper {
	@Select("SELECT no, subject, name, TO_CHAR(regdate,'yyyy-MM-dd') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM goods_board ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
	
	@Select("SELECT COUNT(*) FROM goods_board")
	public int boardRowCount();
	
	@Insert("INSERT INTO goods_board(no, name, subject, content, pwd) VALUES ("
			+ "(SELECT NVL(MAX(no)+1,1) FROM goods_board),"
			+ "#{name},#{subject},#{content},#{pwd})")
	public void boardInsert(BoardVO vo);
	
	@Update("UPDATE goods_board SET hit=hit+1 WHERE no=#{no}")
	public void hitIncrement(int no);
	
	//num값을 기억해서 detail값에 넣어주기 위함
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate,'yyyy-MM-dd') as dbday, hit, num "
			+ "FROM (SELECT no, name, subject, content, regdate, hit, rownum as num "
			+ "FROM (SELECT no, name, subject, content, regdate, hit "
			+ "FROM goods_board ORDER BY no DESC)) "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	@Select("SELECT no, subject, num "
			+ "FROM (SELECT no, subject, rownum as num "
			+ "FROM (SELECT no, subject "
			+ "FROM goods_board ORDER BY no DESC)) "
			+ "WHERE num=#{num}")
	public BoardVO boardPNData(int num); //prev,next Data
	
	@Select("SELECT no, subject, hit, rownum "
			+ "FROM (SELECT no, subject, hit "
			+ "FROM goods_board ORDER BY hit DESC) "
			+ "WHERE rownum<=5")
	public List<BoardVO> boardFooterData();//footer에 출력할 데이터
}
