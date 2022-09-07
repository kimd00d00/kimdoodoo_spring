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
	//��� ��� ���
	@Select("SELECT no, bno, id, name, msg, TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday, group_tab, type "
			+ "FROM spring_reply "
			+ "WHERE bno=#{bno} AND type=#{type} "
			+ "ORDER BY group_id DESC, group_step ASC")
	public List<ReplyVO> replyListData(ReplyVO vo);
	
	
	//��� �߰�
	@SelectKey(keyProperty="no", resultType=int.class, before=true, 
			statement="SELECT NVL(MAX(no)+1,1) FROM spring_reply")
	@Insert("INSERT INTO spring_reply(no, bno, id, name, msg, group_id, type) VALUES("
			+ "#{no}, #{bno}, #{id}, #{name}, #{msg}, (SELECT NVL(MAX(group_id)+1,1) FROM spring_reply), #{type})")
	public void replyInsert(ReplyVO vo);
	
	
	//��� ����
	@Update("UPDATE spring_reply SET "
			+ "msg=#{msg} WHERE no=#{no}")
	public void replyUpdate(ReplyVO vo);

	
	//���� �ޱ� (TRANSACTION***)
	//pno�� group_id, group_step, group_tab ��������
	@Select("SELECT group_id, group_step, group_tab FROM spring_reply WHERE no=#{no}")
	public ReplyVO replyParentInfoData(int no);
	//group_step ����
	@Update("UPDATE spring_reply SET group_step=group_step+1 "
			+ "WHERE group_id=#{group_id} AND group_step>#{group_step}")
	public void replyGroupStepIncrement(ReplyVO vo);
	//insert!
	@Insert("INSERT INTO spring_reply(no, bno, id, name, msg, group_id, type, group_step, group_tab, root) VALUES("
			+ "(SELECT NVL(MAX(no)+1,1) FROM spring_reply), #{bno}, #{id}, #{name}, #{msg}, #{group_id}, #{type}, #{group_step}, #{group_tab}, #{root})")
	public void replyreplyInsert(ReplyVO vo);
	//depth ����
	@Update("UPDATE spring_reply SET depth=depth+1 WHERE no=#{no}")
	public void replyDepthIncrement(int no);
	
	
	//��� ���� (TRANSACTION***)
	//no�� depth, root ��������. depth>0�̸� ������Ʈ, depth==0�̸� �����Ұ�
	@Select("SELECT depth, root FROM spring_reply WHERE no=#{no}")
	public ReplyVO replyInfoData(int no);
	//depth>0�϶� - update!
	@Update("UPDATE spring_reply SET msg='�����ڰ� ������ ����Դϴ�' WHERE no=#{no}")
	public void replyMsgUpdate(int no);
	//depth=0�϶� - delete!
	@Delete("DELETE FROM spring_reply WHERE no=#{no}")
	public void replyDelete(int no);
	//depth ���ҽ�Ŵ
	@Update("UPDATE spring_reply SET depth=depth-1 WHERE no=#{no}")
	public void replyDepthDecrement(int no);
}
