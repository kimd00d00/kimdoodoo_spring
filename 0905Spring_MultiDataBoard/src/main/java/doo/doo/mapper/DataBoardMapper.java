package doo.doo.mapper;
import java.util.*;
import doo.doo.dao.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface DataBoardMapper {
	@Select("SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM spring_databoard ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> boardListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_databoard")
	public int boardTotalPage();
	
	//자동증가번호(시퀀스)
	@SelectKey(keyProperty="no", resultType=int.class, before=true,
			statement="SELECT NVL(MAX(no)+1,1) as no FROM spring_databoard")
	@Insert("INSERT INTO spring_databoard VALUES("
			+ "#{no},#{name},#{subject},#{content},#{pwd},SYSDATE,0,#{filename},"
			+ "#{filesize},#{filecount})")
	public void boardInsert(DataBoardVO vo);
	
	//상세보기
	@Update("UPDATE spring_databoard SET hit=hit+1 WHERE no=#{no}")
	public void hitIncrement(int no);
	@Select("SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD') as dbday, "
			+ "filename, filesize, filecount "
			+ "FROM spring_databoard WHERE no=#{no}")
	public DataBoardVO boardDetailData(int no);
	
	@Select("SELECT pwd FROM spring_databoard WHERE no=#{no}")
	public String boardGetPassword(int no);
	
	@Update("UPDATE spring_databoard SET "
			+ "name=#{name}, subject=#{subject}, content=#{content} "
			+ "WHERE no=#{no}")
	public void boardUpdate(DataBoardVO vo);
	
	@Delete("DELETE FROM spring_databoard WHERE no=#{no}")
	public void boardDelete(int no);
	
	@Select("SELECT filename, filecount FROM spring_databoard "
			+ "WHERE no=#{no}")
	public DataBoardVO boardInfoData(int no);
}
