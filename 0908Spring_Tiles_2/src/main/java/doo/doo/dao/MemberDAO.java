package doo.doo.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import doo.doo.vo.*;
import doo.doo.mapper.*;
@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
	public MemberVO isLogin(String id, String pwd) {
		int cnt = mapper.memberIdCount(id);
		MemberVO vo = new MemberVO();
		if(cnt==0) {
			vo.setMsg("NOID");
		}else {
			MemberVO mvo = mapper.memberInfoData(id);
			if(pwd.equals(mvo.getPwd())){
				vo.setMsg("OK");
				vo.setName(mvo.getName());
			}else {
				vo.setMsg("NOPWD");
			}
		}
		return vo;
	}
}
