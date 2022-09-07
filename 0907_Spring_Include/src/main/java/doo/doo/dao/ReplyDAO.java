package doo.doo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import doo.doo.mapper.ReplyMapper;
@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
	//��� ��� ���
	public List<ReplyVO> replyListData(ReplyVO vo){
		return mapper.replyListData(vo);
	}
	//��� �߰�
	public void replyInsert(ReplyVO vo) {
		mapper.replyInsert(vo);
	}
	//��� ����
	public void replyUpdate(ReplyVO vo) {
		mapper.replyUpdate(vo);
	}
	//���� �߰� - transaction! ����Ʈ�� REQUIRED
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void replyreplyInsert(int pno, ReplyVO vo) {
		//�� ��� ���� ��������
		ReplyVO pvo = mapper.replyParentInfoData(pno);
		//group_step ����
		mapper.replyGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pno);
		//INSERT!
		mapper.replyreplyInsert(vo);
		//depth ����
		mapper.replyDepthIncrement(pno);
	}
	
	//��� ����
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void replyDelete(int no) {
		//���� ��� ��� ���� ��������
		ReplyVO vo = mapper.replyInfoData(no);
		if(vo.getDepth()==0) {
			mapper.replyDelete(no);
		}else {
			mapper.replyMsgUpdate(no);
		}
		mapper.replyDepthDecrement(vo.getRoot());
	}
}
