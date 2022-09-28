package doo.doo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import doo.doo.mapper.GoodsMapper;
import java.util.*;

@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	public List<GoodsVO> goodsListData(Map map){
		return mapper.goodsListData(map);
	}
	public int goodsTotalPage(Map map) {
		return mapper.goodsTotalPage(map);
	}
	public GoodsVO goodsDetailData(Map map) {
		return mapper.goodsDetailData(map);
	}
	public MemberVO memberLogin(String id, String pwd) {
		MemberVO vo = new MemberVO();
		int count = mapper.idCount(id);
		if(count==0) { //ID ¾øÀ½
			vo.setMsg("NOID");
		}else {
			MemberVO dbVO = mapper.memberGetPassword(id);
			if(dbVO.getPwd().equals(pwd)) {
				vo.setId(id);
				vo.setName(dbVO.getName());
				vo.setMsg("OK");
			}else {
				vo.setMsg("NOPWD");
			}
		}
		return vo;
	}
	public List<GoodsVO> goodsFindData(Map map){
		return mapper.goodsFindData(map);
	}
}
