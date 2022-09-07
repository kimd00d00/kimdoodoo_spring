package doo.doo.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import doo.doo.dao.*;
public interface BoardMapper {
	//목록 출력
	@Select("SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit, group_tab, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, group_tab, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit, group_tab "
			+ "FROM spring_replyboard ORDER BY group_id DESC, group_step ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
	
	//총 페이지
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_replyboard")
	public int boardTotalPage();
	//글 총 개수
	@Select("SELECT COUNT(*) FROM spring_replyboard")
	public int boardCount();
	//글쓰기
	@Insert("INSERT INTO spring_replyboard(no,name,subject,content,pwd,group_id) "
			+ "VALUES(sr1_no_seq.nextval,#{name},#{subject},#{content},#{pwd},(SELECT NVL(MAX(group_id)+1,1) FROM spring_replyboard))")
	public void boardInsert(BoardVO vo);
	
	//글 상세보기
	@Update("UPDATE spring_replyboard SET hit=hit+1 WHERE no=#{no}")
	public void hitIncrement(int no);
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit "
			+ "FROM spring_replyboard "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	//답글달기 - parent정보
	@Select("SELECT group_id, group_step, group_tab "
			+ "FROM spring_replyboard WHERE no=#{no}")
	public BoardVO boardParentInfoData(int no);
	//답글달기 - group_step 변경
	@Update("UPDATE spring_replyboard SET "
			+ "group_step=group_step+1 "
			+ "WHERE group_id=#{group_id} AND group_step>#{group_step}")
	public void boardGroupStepIncrement(BoardVO vo);
	//답글달기 - 데이터 추가
	@Insert("INSERT INTO spring_replyboard(no,name,subject,content,pwd,group_id,group_step,group_tab,root) "
			+ "VALUES(sr1_no_seq.nextval, #{name},#{subject},#{content},#{pwd},#{group_id},#{group_step},#{group_tab},#{root})")
	public void boardReplyInsert(BoardVO vo);
	//답글달기 - depth 증가
	@Update("UPDATE spring_replyboard SET "
			+ "depth=depth+1 WHERE no=#{no}")
	public void depthIncrement(int no);
	
	//수정 - 비밀번호
	@Select("SELECT pwd FROM spring_replyboard WHERE no=#{no}")
	public String boardGetPassword(int no);
	//수정 - 수정처리
	@Update("UPDATE spring_replyboard SET "
			+ "name=#{name}, subject=#{subject}, content=#{content} "
			+ "WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	
	//삭제 - root, depth확인
	@Select("SELECT root, depth FROM spring_replyboard "
			+ "WHERE no=#{no}")
	public BoardVO boardGetRootDepthData(int no);
	//삭제 - 삭제 처리
	@Delete("DELETE FROM spring_replyboard WHERE no=#{no}")
	public void boardDelete(int no);
	//삭제 - 답글 있는 게시글의 경우 제목 변경
	@Update("UPDATE spring_replyboard SET subject=#{subject}, content=#{content} WHERE no=#{no}")
	public void boardDelete2(BoardVO vo);
	//삭제 - root감소
	@Update("UPDATE spring_replyboard SET "
			+ "depth=depth-1 WHERE no=#{no}")
	public void depthDecrement(int no);
}
