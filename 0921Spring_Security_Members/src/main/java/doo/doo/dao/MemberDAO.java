package doo.doo.dao;
import java.util.*;
import doo.doo.vo.*;
import doo.doo.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
	public int memberIdCheck(String id) {
		return mapper.memberIdCheck(id);
	}
	
	public void memberJoinInsert(MemberVO vo) {
		mapper.memberJoin(vo);
	}
	
	public MemberVO memberJoinInfoData(String id) {
		return mapper.memberJoinInfoData(id);
	}
}
