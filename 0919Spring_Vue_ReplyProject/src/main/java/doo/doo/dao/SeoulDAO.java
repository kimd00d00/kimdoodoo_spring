package doo.doo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import doo.doo.vo.*;
import doo.doo.mapper.*;
@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	
	public List<SeoulVO> seoulListData(Map map){
		return mapper.seoulListData(map);
	}
	
	public int seoulTotalPage(Map map) {
		return mapper.seoulTotalPage(map);
	}
	
	public SeoulVO seoulDetailData(Map map) {
		mapper.hitIncrement(map);
		return mapper.seoulDetailData(map);
	}
	
	public MemberVO isLogin(String id, String pwd) {
		MemberVO vo = new MemberVO();
		int count = mapper.memberIdCheck(id);
		if(count==0) {
			vo.setMsg("NOID");
		}else {//ID 존재함
			MemberVO rvo = mapper.memberPwdCheck(id);
			if(pwd.equals(rvo.getPwd())) {
				vo.setMsg("OK");
				vo.setName(rvo.getName());
				vo.setId(id);
			}else {
				vo.setMsg("NOPWD");
			}
		}
		return vo;
	}
}
