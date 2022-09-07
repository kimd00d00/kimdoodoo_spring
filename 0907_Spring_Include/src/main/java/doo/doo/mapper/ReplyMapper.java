package doo.doo.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import doo.doo.dao.ReplyVO;
/*
 * private int no, bno, group_id, group_step, group_tab, root, depth, type;
	private String id, name, msg, dbday;
	private Date regdate;
 */
public interface ReplyMapper {
	//댓글 목록 출력
	@Select("SELECT no, bno, id, name, msg, TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday, group_tab, type "
			+ "FROM spring_reply "
			+ "WHERE bno=#{bno} AND type=#{type} "
			+ "ORDER BY group_id DESC, group_step ASC")
	public List<ReplyVO> replyListData(ReplyVO vo);
	
	
	//댓글 추가
	@SelectKey(keyProperty="no", resultType=int.class, before=true, 
			statement="SELECT NVL(MAX(no)+1,1) FROM spring_reply")
	@Insert("INSERT INTO spring_reply(no, bno, id, name, msg, group_id, type) VALUES("
			+ "#{no}, #{bno}, #{id}, #{name}, #{msg}, (SELECT NVL(MAX(group_id)+1,1) FROM spring_reply), #{type})")
	public void replyInsert(ReplyVO vo);
	
	
	//댓글 수정
	@Update("UPDATE spring_reply SET "
			+ "msg=#{msg} WHERE no=#{no}")
	public void replyUpdate(ReplyVO vo);

	
	//대댓글 달기 (TRANSACTION***)
	//pno로 group_id, group_step, group_tab 가져오기
	@Select("SELECT group_id, group_step, group_tab FROM spring_reply WHERE no=#{no}")
	public ReplyVO replyParentInfoData(int no);
	//group_step 변경
	@Update("UPDATE spring_reply SET group_step=group_step+1 "
			+ "WHERE group_id=#{group_id} AND group_step>#{group_step}")
	public void replyGroupStepIncrement(ReplyVO vo);
	//insert!
	@Insert("INSERT INTO spring_reply(no, bno, id, name, msg, group_id, type, group_step, group_tab, root) VALUES("
			+ "(SELECT NVL(MAX(no)+1,1) FROM spring_reply), #{bno}, #{id}, #{name}, #{msg}, #{group_id}, #{type}, #{group_step}, #{group_tab}, #{root})")
	public void replyreplyInsert(ReplyVO vo);
	//depth 변경
	@Update("UPDATE spring_reply SET depth=depth+1 WHERE no=#{no}")
	public void replyDepthIncrement(int no);
	
	
	//댓글 삭제 (TRANSACTION***)
	//no로 depth, root 가져오기. depth>0이면 업데이트, depth==0이면 삭제할거
	@Select("SELECT depth, root FROM spring_reply WHERE no=#{no}")
	public ReplyVO replyInfoData(int no);
	//depth>0일때 - update!
	@Update("UPDATE spring_reply SET msg='관리자가 삭제한 댓글입니다' WHERE no=#{no}")
	public void replyMsgUpdate(int no);
	//depth=0일때 - delete!
	@Delete("DELETE FROM spring_reply WHERE no=#{no}")
	public void replyDelete(int no);
	//depth 감소시킴
	@Update("UPDATE spring_reply SET depth=depth-1 WHERE no=#{no}")
	public void replyDepthDecrement(int no);
}
