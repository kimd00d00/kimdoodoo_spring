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
	
	//댓글 목록 출력
	public List<ReplyVO> replyListData(ReplyVO vo){
		return mapper.replyListData(vo);
	}
	//댓글 추가
	public void replyInsert(ReplyVO vo) {
		mapper.replyInsert(vo);
	}
	//댓글 수정
	public void replyUpdate(ReplyVO vo) {
		mapper.replyUpdate(vo);
	}
	//대댓글 추가 - transaction! 디폴트가 REQUIRED
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void replyreplyInsert(int pno, ReplyVO vo) {
		//원 댓글 정보 가져오기
		ReplyVO pvo = mapper.replyParentInfoData(pno);
		//group_step 변경
		mapper.replyGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pno);
		//INSERT!
		mapper.replyreplyInsert(vo);
		//depth 변경
		mapper.replyDepthIncrement(pno);
	}
	
	//댓글 삭제
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void replyDelete(int no) {
		//삭제 대상 댓글 정보 가져오기
		ReplyVO vo = mapper.replyInfoData(no);
		if(vo.getDepth()==0) {
			mapper.replyDelete(no);
		}else {
			mapper.replyMsgUpdate(no);
		}
		mapper.replyDepthDecrement(vo.getRoot());
	}
}
